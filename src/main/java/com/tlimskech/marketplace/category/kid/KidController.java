package com.tlimskech.marketplace.category.kid;

import com.tlimskech.marketplace.storage.FileStorageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/kids")
@Log4j2
public class KidController {

    private final KidService kidService;
    private final FileStorageService fileStorageService;

    public KidController(KidService kidService, FileStorageService fileStorageService) {
        this.kidService = kidService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestPart("kid") Kid kid,
                                              @RequestParam(required = false, name = "file") MultipartFile[] file) {
        log.info("Kid Data to be created: {}", kid);
        List<String> images = fileStorageService.multipleUpload(file, kid.getTitleDescription().getTitle());
        kid.setImages(images);
        Kid saved = kidService.create(kid);
        log.info("Kid data created: {}", kid);
        return ResponseEntity.ok(saved);
    }
}
