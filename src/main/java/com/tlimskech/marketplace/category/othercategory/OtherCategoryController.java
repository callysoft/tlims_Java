package com.tlimskech.marketplace.category.othercategory;

import com.tlimskech.marketplace.storage.FileStorageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/othercategories")
@Log4j2
public class OtherCategoryController {

    private final FileStorageService fileStorageService;
    private final OtherCategoryService othercategoryService;

    public ServicesController(FileStorageService fileStorageService, OtherCategoryService othercategoryService) {
        this.fileStorageService = fileStorageService;
        this.othercategoryService = othercategoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestPart("othercategories") OtherCategory othercategory,
                                    @RequestParam(required = false, name = "file") MultipartFile[] file) {
        log.info("OtherCategory to create {}", othercategories);
        List<String> images = fileStorageService.multipleUpload(file, othercategories.getTitleDescription().getTitle());
        othercategories.setImages(images);
        OtherCategory saved = othercategoryService.create(othercategories);
        log.info("OtherCategory created: {}", saved);
        return ResponseEntity.ok(saved);
    }
}
