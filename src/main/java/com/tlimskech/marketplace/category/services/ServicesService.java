package com.tlimskech.marketplace.category.services;

import com.tlimskech.marketplace.ad.AdService;
import com.tlimskech.marketplace.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicesService implements BaseService<Services, Long> {

    private final AdService adService;
    private final ServicesRepository servicesRepository;

    public ServicesService(AdService adService, ServicesRepository servicesRepository) {
        this.adService = adService;
        this.servicesRepository = servicesRepository;
    }

    @Override
    public Services create(Services services) {
        return (Services) adService.create(services);
    }

    @Override
    public Services update(Services services) {
        return null;
    }

    @Override
    public void delete(Services services) {

    }

    @Override
    public Page<Services> findAll(Services services, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Services> findById(Long aLong) {
        return Optional.empty();
    }
}
