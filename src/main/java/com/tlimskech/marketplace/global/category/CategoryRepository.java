package com.tlimskech.marketplace.global.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long>, QuerydslPredicateExecutor<Category> {

    List<Category> findByParentCategory_Id(Long parentId);
    List<Category> findByParentCategoryIsNull();
}
