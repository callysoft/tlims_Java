package com.tlimskech.marketplace.category.pet;

import com.tlimskech.marketplace.storage.FileStorageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@Log4j2
public class PetController {

    private final PetService petService;
    private final FileStorageService fileStorageService;

    public PetController(PetService petService, FileStorageService fileStorageService) {
        this.petService = petService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestPart("pet") Pet pet,
                                    @RequestParam(required = false, name = "file") MultipartFile[] file) {
        log.info("Pet to create: {}", pet);
        List<String> images = fileStorageService.multipleUpload(file, pet.getTitleDescription().getTitle());
        pet.setImages(images);
        Pet saved = petService.create(pet);
        log.info("Pet created: {}", saved);
        return ResponseEntity.ok(saved);
    }
}
