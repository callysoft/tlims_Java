package com.tlimskech.marketplace.category.fashion;

import com.tlimskech.marketplace.storage.FileStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/fashions")
public class FashionController {

    private final FashionService fashionService;
    private final FileStorageService fileStorageService;

    public FashionController(FashionService fashionService, FileStorageService fileStorageService) {
        this.fashionService = fashionService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createFashion(@RequestPart("fashion") Fashion fashion,
                                              @RequestParam(required = false, name = "file") MultipartFile[] file) throws IOException {
        List<String> images = fileStorageService.multipleUpload(file, fashion.getTitleDescription().getTitle());
        fashion.setImages(images);
        return ResponseEntity.ok(fashionService.create(fashion));
    }
}
