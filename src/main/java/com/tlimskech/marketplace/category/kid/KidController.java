package com.tlimskech.marketplace.category.kid;

import com.tlimskech.marketplace.storage.FileStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/kids")
public class KidController {

    private final KidService kidService;
    private final FileStorageService fileStorageService;

    public KidController(KidService kidService, FileStorageService fileStorageService) {
        this.kidService = kidService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestPart("kid") Kid kid,
                                              @RequestParam(required = false, name = "file") MultipartFile[] file) throws IOException {
        List<String> images = fileStorageService.multipleUpload(file, kid.getTitleDescription().getTitle());
        kid.setImages(images);
        return ResponseEntity.ok(kidService.create(kid));
    }
}
