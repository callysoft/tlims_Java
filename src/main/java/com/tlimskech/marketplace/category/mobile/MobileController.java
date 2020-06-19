package com.tlimskech.marketplace.category.mobile;

import com.tlimskech.marketplace.storage.FileStorageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/mobiles")
@Log4j2
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
        log.info("Mobile to create {}", mobile);
        List<String> images = fileStorageService.multipleUpload(file, mobile.getTitleDescription().getTitle());
        mobile.setImages(images);
        Mobile saved = mobileService.create(mobile);
        log.info("Mobile created {}", saved);
        return ResponseEntity.ok(saved);
    }
}
