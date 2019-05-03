package com.tlimskech.marketplace.category.mobile;

import com.tlimskech.marketplace.storage.FileStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/mobiles")
public class MobileController {

    private final MobileService mobileService;
    private final FileStorageService fileStorageService;

    public MobileController(MobileService mobileService, FileStorageService fileStorageService) {
        this.mobileService = mobileService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestPart("mobile") Mobile mobile,
                                    @RequestParam(required = false, name = "file") MultipartFile[] file) {
        List<String> images = fileStorageService.multipleUpload(file, mobile.getTitleDescription().getTitle());
        mobile.setImages(images);
        return ResponseEntity.ok(mobileService.create(mobile));
    }
}
