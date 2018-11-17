package com.tlimskech.marketplace.category.beautyhealth;

import com.tlimskech.marketplace.ad.AdService;
import com.tlimskech.marketplace.core.data.SearchRequest;
import com.tlimskech.marketplace.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BeautyService implements BaseService<Beauty, Long> {

    private final BeautyRepository beautyRepository;
    private final AdService adService;

    public BeautyService(BeautyRepository beautyRepository, AdService adService) {
        this.beautyRepository = beautyRepository;
        this.adService = adService;
    }

    @Override
    public Beauty create(Beauty beauty) {
        return null;
    }

    @Override
    public Beauty update(Beauty beauty) {
        return null;
    }

    @Override
    public void delete(Beauty beauty) {

    }

    @Override
    public Page<Beauty> findAll(Beauty beauty, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Beauty> findById(Long aLong) {
        return null;
    }

    @Override
    public Page<Beauty> findAll(SearchRequest request, Pageable pageable) {
        return null;
    }
}
