package com.tlimskech.marketplace.category.homegarden;

import com.tlimskech.marketplace.ad.AdService;
import com.tlimskech.marketplace.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HomeGardenService implements BaseService<HomeGarden, Long> {

    private final HomeGardenRepository homeGardenRepository;
    private final AdService adService;

    public HomeGardenService(HomeGardenRepository homeGardenRepository, AdService adService) {
        this.homeGardenRepository = homeGardenRepository;
        this.adService = adService;
    }

    @Override
    public HomeGarden create(HomeGarden homeGarden) {
        return (HomeGarden) adService.create(homeGarden);
    }

    @Override
    public HomeGarden update(HomeGarden homeGarden) {
        return null;
    }

    @Override
    public void delete(HomeGarden homeGarden) {

    }

    @Override
    public Page<HomeGarden> findAll(HomeGarden homeGarden, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<HomeGarden> findById(Long aLong) {
        return Optional.empty();
    }
}
