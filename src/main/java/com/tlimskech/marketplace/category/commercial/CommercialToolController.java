package com.tlimskech.marketplace.category.commercial;

import com.tlimskech.marketplace.storage.FileStorageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/commercials")
@Log4j2
public class CommercialToolController {

    private final FileStorageService fileStorageService;
    private final CommercialToolService commercialToolService;

    public CommercialToolController(FileStorageService fileStorageService, CommercialToolService commercialToolService) {
        this.fileStorageService = fileStorageService;
        this.commercialToolService = commercialToolService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createFashion(@RequestPart("commercial") CommercialTool commercialTool,
                                           @RequestParam(required = false, name = "file") MultipartFile[] file) {
        log.info("Commercial tool create:: {}", commercialTool);
        List<String> images = fileStorageService.multipleUpload(file, commercialTool.getTitleDescription().getTitle());
        commercialTool.setImages(images);
        CommercialTool saved = commercialToolService.create(commercialTool);
        log.info("Commercial created:: {}", saved);
        return ResponseEntity.ok(saved);
    }
}
