package com.tlimskech.marketplace.category.beautyhealth;

import com.tlimskech.marketplace.storage.FileStorageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/beauties")
@Log4j2
public class BeautyController {

    private final BeautyService beautyService;
    private final FileStorageService fileStorageService;

    public BeautyController(BeautyService beautyService, FileStorageService fileStorageService) {
        this.beautyService = beautyService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestPart("beauty") Beauty beauty,
                                              @RequestParam(required = false, name = "file") MultipartFile[] file) {
        log.info("Beauty to be created: {}", beauty);
        List<String> images = fileStorageService.multipleUpload(file, beauty.getTitleDescription().getTitle());
        beauty.setImages(images);
        Beauty saved = beautyService.create(beauty);
        log.info("Beauty created: {}", saved);
        return ResponseEntity.ok(saved);
    }
}
