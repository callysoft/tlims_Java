package com.tlimskech.marketplace.ad;

import com.tlimskech.marketplace.core.data.SearchRequest;
import com.tlimskech.marketplace.core.service.BaseService;
import com.tlimskech.marketplace.exception.ApplicationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdService implements BaseService<Ad, Long> {

    private final AdRepository adRepository;

    public AdService(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public Ad create(Ad ad) {
        return adRepository.save(ad);
    }

    @Override
    public Ad update(Ad ad) {
        Ad found = adRepository.findById(ad.getId()).orElseThrow(() -> new ApplicationException("Resource not found"));
        found.copyForUpdate(ad);
        return adRepository.save(found);
    }

    @Override
    public void delete(Ad ad) {

    }

    @Override
    public Page<Ad> findAll(Ad ad, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Ad> findById(Long id) {
        return adRepository.findById(id);
    }

    @Override
    public Page<Ad> findAll(SearchRequest request, Pageable pageable) {
        return null;
    }
}
