package com.tlimskech.marketplace.category.vehicle;

import com.tlimskech.marketplace.ad.AdService;
import com.tlimskech.marketplace.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleService implements BaseService<Vehicle, Long> {

    private final AdService adService;
    private final VehicleRepository vehicleRepository;

    public VehicleService(AdService adService, VehicleRepository vehicleRepository) {
        this.adService = adService;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle create(Vehicle vehicle) {
        return (Vehicle) adService.create(vehicle);
    }

    @Override
    public Vehicle update(Vehicle vehicle) {
        return null;
    }

    @Override
    public void delete(Vehicle vehicle) {

    }

    @Override
    public Page<Vehicle> findAll(Vehicle vehicle, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Vehicle> findById(Long aLong) {
        return null;
    }
}
