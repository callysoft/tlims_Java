package com.tlimskech.marketplace.category.services;

import com.tlimskech.marketplace.storage.FileStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/services")
public class ServicesController {

    private final FileStorageService fileStorageService;
    private final ServicesService servicesService;

    public ServicesController(FileStorageService fileStorageService, ServicesService servicesService) {
        this.fileStorageService = fileStorageService;
        this.servicesService = servicesService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestPart("services") Services services,
                                    @RequestParam(required = false, name = "file") MultipartFile[] file) {
        List<String> images = fileStorageService.multipleUpload(file, services.getTitleDescription().getTitle());
        services.setImages(images);
        return ResponseEntity.ok(servicesService.create(services));
    }
}
