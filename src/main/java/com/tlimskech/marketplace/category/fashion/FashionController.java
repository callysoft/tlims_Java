package com.tlimskech.marketplace.category.fashion;

import com.tlimskech.marketplace.storage.FileStorageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/fashions")
@Log4j2
public class FashionController {

    private final FashionService fashionService;
    private final FileStorageService fileStorageService;

    public FashionController(FashionService fashionService, FileStorageService fileStorageService) {
        this.fashionService = fashionService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createFashion(@RequestPart("fashion") Fashion fashion,
                                              @RequestParam(required = false, name = "file") MultipartFile[] file) {
        log.info("Fashion to create: {}", fashion);
        System.out.println("File Gotten " + Arrays.toString(file));
        List<String> images = fileStorageService.multipleUpload(file, fashion.getTitleDescription().getTitle());
        fashion.setImages(images);
        Fashion saved = fashionService.create(fashion);
        log.info("Fashion created: {}", saved);
        return ResponseEntity.ok(saved);
    }
}
