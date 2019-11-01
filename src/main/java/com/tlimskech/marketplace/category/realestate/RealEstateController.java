package com.tlimskech.marketplace.category.realestate;

import com.tlimskech.marketplace.storage.FileStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/realestates")
public class RealEstateController {

    private final RealEstateService realEstateService;
    private final FileStorageService fileStorageService;

    public RealEstateController(RealEstateService realEstateService, FileStorageService fileStorageService) {
        this.realEstateService = realEstateService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestPart("realestate") RealEstate realEstate,
                                    @RequestParam(required = false, name = "file") MultipartFile[] file) {
        List<String> images = fileStorageService.multipleUpload(file, realEstate.getTitleDescription().getTitle());
        realEstate.setImages(images);
        return ResponseEntity.ok(realEstateService.create(realEstate));
    }
}
