package com.tlimskech.marketplace.category.repair;

import com.tlimskech.marketplace.storage.FileStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/repairs")
public class RepairController {

    private final RepairService repairService;
    private final FileStorageService fileStorageService;

    public RepairController(RepairService repairService, FileStorageService fileStorageService) {
        this.repairService = repairService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestPart("repair") Repair repair,
                                    @RequestParam(required = false, name = "file") MultipartFile[] file) throws IOException {
        List<String> images = fileStorageService.multipleUpload(file, repair.getTitleDescription().getTitle());
        repair.setImages(images);
        return ResponseEntity.ok(repairService.create(repair));
    }
}
