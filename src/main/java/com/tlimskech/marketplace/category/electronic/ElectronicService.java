package com.tlimskech.marketplace.category.electronic;

import com.tlimskech.marketplace.ad.AdService;
import com.tlimskech.marketplace.core.data.SearchRequest;
import com.tlimskech.marketplace.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ElectronicService implements BaseService<Electronic, Long> {

    private final ElectronicRepository electronicRepository;
    private final AdService adService;

    public ElectronicService(ElectronicRepository electronicRepository, AdService adService) {
        this.electronicRepository = electronicRepository;
        this.adService = adService;
    }

    @Override
    public Electronic create(Electronic electronic) {
        return (Electronic) adService.create(electronic);
    }

    @Override
    public Electronic update(Electronic electronic) {
        return null;
    }

    @Override
    public void delete(Electronic electronic) {

    }

    @Override
    public Page<Electronic> findAll(Electronic electronic, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Electronic> findById(Long aLong) {
        return null;
    }

    @Override
    public Page<Electronic> findAll(SearchRequest request, Pageable pageable) {
        return null;
    }
}
