package com.tlimskech.marketplace.category.vehicle;

import com.tlimskech.marketplace.storage.FileStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("vehicles")
public class VehicleController {

    private final VehicleService vehicleService;
    private final FileStorageService fileStorageService;

    public VehicleController(VehicleService vehicleService, FileStorageService fileStorageService) {
        this.vehicleService = vehicleService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestPart("vehicle") Vehicle vehicle,
                                    @RequestParam(required = false, name = "file") MultipartFile[] file) throws IOException {
        List<String> images = fileStorageService.multipleUpload(file, vehicle.getTitleDescription().getTitle());
        vehicle.setImages(images);
        return ResponseEntity.ok(vehicleService.create(vehicle));
    }
}
