package com.tlimskech.marketplace.category.homegarden;

import com.tlimskech.marketplace.storage.FileStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/gardens")
public class HomeGardenController {

    private final HomeGardenService homeGardenService;
    private final FileStorageService fileStorageService;

    public HomeGardenController(HomeGardenService homeGardenService, FileStorageService fileStorageService) {
        this.homeGardenService = homeGardenService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createFashion(@RequestPart("garden") HomeGarden homeGarden,
                                           @RequestParam(required = false, name = "file") MultipartFile[] file) {
        List<String> images = fileStorageService.multipleUpload(file, homeGarden.getTitleDescription().getTitle());
        homeGarden.setImages(images);
        return ResponseEntity.ok(homeGardenService.create(homeGarden));
    }
}
