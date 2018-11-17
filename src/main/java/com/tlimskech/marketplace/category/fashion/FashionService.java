package com.tlimskech.marketplace.category.fashion;

import com.tlimskech.marketplace.ad.AdService;
import com.tlimskech.marketplace.core.data.SearchRequest;
import com.tlimskech.marketplace.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FashionService implements BaseService<Fashion, Long> {

    private final AdService adService;
    private final FashionRepository fashionRepository;

    public FashionService(AdService adService, FashionRepository fashionRepository) {
        this.adService = adService;
        this.fashionRepository = fashionRepository;
    }

    @Override
    public Fashion create(Fashion fashion) {
        return (Fashion) adService.create(fashion);
    }

    @Override
    public Fashion update(Fashion fashion) {
        return null;
    }

    @Override
    public void delete(Fashion fashion) {

    }

    @Override
    public Page<Fashion> findAll(Fashion fashion, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Fashion> findById(Long aLong) {
        return null;
    }

    @Override
    public Page<Fashion> findAll(SearchRequest request, Pageable pageable) {
        return null;
    }
}
