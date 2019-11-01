package com.tlimskech.marketplace.category.pet;

import com.tlimskech.marketplace.ad.AdService;
import com.tlimskech.marketplace.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetService implements BaseService<Pet, Long> {

    private final AdService adService;
    private final PetRepository petRepository;

    public PetService(AdService adService, PetRepository petRepository) {
        this.adService = adService;
        this.petRepository = petRepository;
    }

    @Override
    public Pet create(Pet pet) {
        return (Pet) adService.create(pet);
    }

    @Override
    public Pet update(Pet pet) {
        return null;
    }

    @Override
    public void delete(Pet pet) {

    }

    @Override
    public Page<Pet> findAll(Pet pet, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Pet> findById(Long aLong) {
        return Optional.empty();
    }
}
