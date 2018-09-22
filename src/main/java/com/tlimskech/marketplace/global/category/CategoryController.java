package com.tlimskech.marketplace.global.category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("findByParentCategory/{parentId}")
    public ResponseEntity<?> findByParentCategory(@PathVariable("parentId") Long parentId) {
        return ResponseEntity.ok(categoryService.findByParentCategory(parentId));
    }
}
