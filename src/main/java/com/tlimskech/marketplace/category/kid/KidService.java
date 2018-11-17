package com.tlimskech.marketplace.category.kid;

import com.tlimskech.marketplace.core.data.SearchRequest;
import com.tlimskech.marketplace.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KidService implements BaseService<Kid, Long> {

    private final KidRepository kidRepository;

    public KidService(KidRepository kidRepository) {
        this.kidRepository = kidRepository;
    }

    @Override
    public Kid create(Kid kid) {
        return null;
    }

    @Override
    public Kid update(Kid kid) {
        return null;
    }

    @Override
    public void delete(Kid kid) {

    }

    @Override
    public Page<Kid> findAll(Kid kid, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Kid> findById(Long id) {
        return kidRepository.findById(id);
    }

    @Override
    public Page<Kid> findAll(SearchRequest request, Pageable pageable) {
        return null;
    }
}
