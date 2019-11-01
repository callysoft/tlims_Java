package com.tlimskech.marketplace.category.realestate;

import com.tlimskech.marketplace.ad.AdService;
import com.tlimskech.marketplace.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RealEstateService implements BaseService<RealEstate, Long> {

    private final RealEstateRepository realEstateRepository;
    private final AdService adService;

    public RealEstateService(RealEstateRepository realEstateRepository, AdService adService) {
        this.realEstateRepository = realEstateRepository;
        this.adService = adService;
    }

    @Override
    public RealEstate create(RealEstate realEstate) {
        return (RealEstate) adService.create(realEstate);
    }

    @Override
    public RealEstate update(RealEstate realEstate) {
        return null;
    }

    @Override
    public void delete(RealEstate realEstate) {

    }

    @Override
    public Page<RealEstate> findAll(RealEstate realEstate, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<RealEstate> findById(Long aLong) {
        return Optional.empty();
    }
}
