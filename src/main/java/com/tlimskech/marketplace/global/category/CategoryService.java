package com.tlimskech.marketplace.global.category;

import com.tlimskech.marketplace.core.data.SearchRequest;
import com.tlimskech.marketplace.core.service.BaseService;
import com.tlimskech.marketplace.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements BaseService<Category, Long> {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        Category category1 = categoryRepository.findById(category.getId()).orElseThrow(() -> new ApplicationException("Resource not found"));
        category1.copyForUpdate(category);
        return categoryRepository.save(category1);
    }

    @Override
    public void delete(Category category) {

    }

    public void deleteAll() {
        categoryRepository.deleteAll();
    }

    @Override
    public Page<Category> findAll(Category category, Pageable pageable) {
        return null;
    }

    @Override
    public Collection<Category> findAll() {
        return categoryRepository.findByParentCategoryIsNull();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Page<Category> findAll(SearchRequest request, Pageable p) {
        return categoryRepository.findAll(new Category().searchPredicate(request), p);
    }

    List<Category> findByParentCategory(Long parentId) {
        return categoryRepository.findByParentCategory_Id(parentId);
    }
}
