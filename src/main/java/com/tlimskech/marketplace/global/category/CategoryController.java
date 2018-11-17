package com.tlimskech.marketplace.global.category;

import com.tlimskech.marketplace.core.data.SearchRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("list")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PostMapping("create")
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
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
}
