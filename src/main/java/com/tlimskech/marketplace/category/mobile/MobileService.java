package com.tlimskech.marketplace.category.mobile;

import com.tlimskech.marketplace.ad.AdService;
import com.tlimskech.marketplace.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MobileService implements BaseService<Mobile, Long> {

    private final AdService adService;
    private final MobileRepository mobileRepository;

    public MobileService(AdService adService, MobileRepository mobileRepository) {
        this.adService = adService;
        this.mobileRepository = mobileRepository;
    }

    @Override
    public Mobile create(Mobile mobile) {
        return (Mobile) adService.create(mobile);
    }

    @Override
    public Mobile update(Mobile mobile) {
        return null;
    }

    @Override
    public void delete(Mobile mobile) {

    }

    @Override
    public Page<Mobile> findAll(Mobile mobile, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Mobile> findById(Long aLong) {
        return null;
    }
}
