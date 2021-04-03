package com.tlimskech.marketplace.category.services;

import com.tlimskech.marketplace.ad.AdService;
import com.tlimskech.marketplace.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OtherCategoryService implements BaseService<Services, Long> {

    private final AdService adService;
    private final OtherCategoryRepository othercategoryRepository;

    public ServicesService(AdService adService, OtherCategoryRepository othercategoryRepository) {
        this.adService = adService;
        this.othercategoryRepository = othercategoryRepository;
    }

    @Override
    public Services create(OtherCataegory othercategory) {
        return (OtherCategory) adService.create(othercategory);
    }

    @Override
    public Services update(OtherCategory othercategory) {
        return null;
    }

    @Override
    public void delete(OtherCategory othercategory) {

    }

    @Override
    public Page<OtherCategory> findAll(OtherCategory othercategory, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<OtherCategory> findById(Long aLong) {
        return Optional.empty();
    }
}
