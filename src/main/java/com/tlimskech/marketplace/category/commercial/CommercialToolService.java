package com.tlimskech.marketplace.category.commercial;

import com.tlimskech.marketplace.ad.AdService;
import com.tlimskech.marketplace.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommercialToolService implements BaseService<CommercialTool, Long> {

    private final AdService adService;
    private final CommercialToolRepository commercialToolRepository;

    public CommercialToolService(AdService adService, CommercialToolRepository commercialToolRepository) {
        this.adService = adService;
        this.commercialToolRepository = commercialToolRepository;
    }

    @Override
    public CommercialTool create(CommercialTool commercialTool) {
        return (CommercialTool) adService.create(commercialTool);
    }

    @Override
    public CommercialTool update(CommercialTool commercialTool) {
        return null;
    }

    @Override
    public void delete(CommercialTool commercialTool) {

    }

    @Override
    public Page<CommercialTool> findAll(CommercialTool commercialTool, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<CommercialTool> findById(Long aLong) {
        return null;
    }
}
