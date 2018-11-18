package com.tlimskech.marketplace.category.repair;

import com.tlimskech.marketplace.ad.AdService;
import com.tlimskech.marketplace.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RepairService implements BaseService<Repair, Long> {

    private final RepairRepository repairRepository;
    private final AdService adService;

    public RepairService(RepairRepository repairRepository, AdService adService) {
        this.repairRepository = repairRepository;
        this.adService = adService;
    }

    @Override
    public Repair create(Repair repair) {
        return (Repair) adService.create(repair);
    }

    @Override
    public Repair update(Repair repair) {
        return null;
    }

    @Override
    public void delete(Repair repair) {

    }

    @Override
    public Page<Repair> findAll(Repair repair, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Repair> findById(Long aLong) {
        return null;
    }
}
