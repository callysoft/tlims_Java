package com.tlimskech.marketplace.global.category;

import com.tlimskech.marketplace.core.data.SearchRequest;
import com.tlimskech.marketplace.storage.FileStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final FileStorageService fileStorageService;
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryService categoryService, FileStorageService fileStorageService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.fileStorageService = fileStorageService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("categories")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PostMapping("create")
    public ResponseEntity<?> createCategory(@RequestPart("category") Category category, @RequestPart(name = "file", required = false) MultipartFile multipartFile) {
        String upload="";
        if (!ObjectUtils.isEmpty(multipartFile)) {
            upload = fileStorageService.singleUpload(multipartFile, category.getCategoryCode().getDataCode());
        }
        category.setImage(upload);
        return ResponseEntity.ok(categoryService.create(category));
    }

    @PostMapping("/findAll")
    public ResponseEntity<?> findAll(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(categoryService.findAll(searchRequest, searchRequest.getPaging().getPageRequest()));
    }

    @GetMapping("findByParentCategory/{parentId}")
    public ResponseEntity<?> findByParentCategory(@PathVariable("parentId") Long parentId) {
        return ResponseEntity.ok(categoryService.findByParentCategory(parentId));
    }


    @GetMapping("/subcategories/{categoryCode}")
    public ResponseEntity<?> findCategoryByPk(@PathVariable("categoryCode") String categoryCode) {
        return ResponseEntity.ok(categoryRepository.findByParentCategory_CategoryCode_DataCodeAndStatusIsTrue(categoryCode));
    }
}
