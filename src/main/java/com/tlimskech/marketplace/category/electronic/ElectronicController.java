package com.tlimskech.marketplace.category.electronic;

import com.tlimskech.marketplace.storage.FileStorageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/electronics")
@Log4j2
public class ElectronicController {

    private final ElectronicService electronicService;
    private final FileStorageService fileStorageService;

    public ElectronicController(ElectronicService electronicService, FileStorageService fileStorageService) {
        this.electronicService = electronicService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createElectronic(@RequestPart("electronic") Electronic electronic,
                                              @RequestParam(required = false, name = "file") MultipartFile[] file) {
        log.info("Electronics to be created:: {}", electronic);
        List<String> images = fileStorageService.multipleUpload(file, electronic.getTitleDescription().getTitle());
        electronic.setImages(images);
        Electronic saved = electronicService.create(electronic);
        log.info("Electronics to be created:: {}", saved);
        return ResponseEntity.ok(saved);
    }

}
