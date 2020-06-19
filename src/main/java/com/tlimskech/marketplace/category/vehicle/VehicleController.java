package com.tlimskech.marketplace.category.vehicle;

import com.tlimskech.marketplace.storage.FileStorageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/vehicles")
@Log4j2
public class VehicleController {

    private final VehicleService vehicleService;
    private final FileStorageService fileStorageService;

    public VehicleController(VehicleService vehicleService, FileStorageService fileStorageService) {
        this.vehicleService = vehicleService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestPart("vehicle") Vehicle vehicle,
                                    @RequestParam(required = false, name = "file") MultipartFile[] file) {
        log.info("Vehicle to create {}", vehicle);
        List<String> images = fileStorageService.multipleUpload(file, vehicle.getTitleDescription().getTitle());
        vehicle.setImages(images);
        Vehicle saved = vehicleService.create(vehicle);
        log.info("Vehicle created {}", saved);
        return ResponseEntity.ok(saved);
    }
}
