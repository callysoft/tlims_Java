package com.tlimskech.marketplace.category.electronic;

import com.tlimskech.marketplace.storage.FileStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/electronics")
public class ElectronicController {

    private final ElectronicService electronicService;
    private final FileStorageService fileStorageService;

    public ElectronicController(ElectronicService electronicService, FileStorageService fileStorageService) {
        this.electronicService = electronicService;
        this.fileStorageService = fileStorageService;
    }

//    /* ELETRONICS */
//    @GetMapping
//    public ResponseEntity<?> getAllElectronics(
//            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
//        return ResponseEntity.ok(electronicService.findAll(PageAttribute.builder().page(page).size(size).build()));
//    }
//
//    @GetMapping("/history")
//    public ResponseEntity<?> getAllElectronics(@RequestParam(name = "searchTerm", required = false) String searchTerm,
//                                               @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
//                                               @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
//                                               @RequestParam(name = "order", required = false, defaultValue = "-id") String order) {
//        return ResponseEntity.ok(electronicService.findAll(searchTerm, PageAttribute.builder().page(page).size(size).order(order).build()));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getAllElectronicsById(@PathVariable("id") Long id) {
//        return ResponseEntity.ok(
//                electronicService.findOne(id).isPresent() ? electronicService.findOne(id).get() : HttpStatus.NOT_FOUND);
//    }

    @PostMapping("/create")
    public ResponseEntity<?> createElectronic(@RequestPart("electronic") Electronic electronic,
                                              @RequestParam(required = false, name = "file") MultipartFile[] file) {
        List<String> images = fileStorageService.multipleUpload(file, electronic.getTitleDescription().getTitle());
        electronic.setImages(images);
        return ResponseEntity.ok(electronicService.create(electronic));
    }

//    @GetMapping("/count")
//    public ResponseEntity<?> getElectronicCount() {
//        return ResponseEntity.ok(electronicService.countAll());
//    }
}
