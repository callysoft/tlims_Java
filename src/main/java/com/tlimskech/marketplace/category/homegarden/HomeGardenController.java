package com.tlimskech.marketplace.category.homegarden;

import com.tlimskech.marketplace.storage.FileStorageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/gardens")
@Log4j2
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
        log.info("Home garden to create: {}", homeGarden);
        List<String> images = fileStorageService.multipleUpload(file, homeGarden.getTitleDescription().getTitle());
        homeGarden.setImages(images);
        HomeGarden saved = homeGardenService.create(homeGarden);
        log.info("Home garden created: {}", saved);
        return ResponseEntity.ok(saved);
    }
}
