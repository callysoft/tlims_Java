package com.tlimskech.marketplace.category.commercial;

import com.tlimskech.marketplace.storage.FileStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/commercials")
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
        List<String> images = fileStorageService.multipleUpload(file, commercialTool.getTitleDescription().getTitle());
        commercialTool.setImages(images);
        return ResponseEntity.ok(commercialToolService.create(commercialTool));
    }
}
