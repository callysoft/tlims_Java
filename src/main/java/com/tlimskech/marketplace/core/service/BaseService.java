package com.tlimskech.marketplace.core.service;

import com.tlimskech.marketplace.core.data.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public interface BaseService<T, ID> {
    T create(T t);
    T update(T t);
    void delete(T t);
    Page<T> findAll(T t, Pageable pageable);
    default Collection<T> findAll() {
        return Collections.emptyList();
    }
    Optional<T> findById(ID id);
    Page<T> findAll(SearchRequest request, Pageable pageable);
}
