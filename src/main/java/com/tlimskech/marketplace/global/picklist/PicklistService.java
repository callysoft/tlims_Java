package com.tlimskech.marketplace.global.picklist;

import com.tlimskech.marketplace.core.data.SearchRequest;
import com.tlimskech.marketplace.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PicklistService implements BaseService<Picklist, Long> {

    private final PicklistRepository picklistRepository;

    public PicklistService(PicklistRepository picklistRepository) {
        this.picklistRepository = picklistRepository;
    }

    @Override
    public Picklist create(Picklist picklist) {
        return picklistRepository.save(picklist);
    }

    @Override
    public Picklist update(Picklist picklist) {
        Picklist found = this.findById(picklist.getId()).orElseThrow(() -> new IllegalArgumentException("Resource not found"));
        found.copyForUpdate(picklist);
        return picklistRepository.save(found);
    }

    @Override
    public void delete(Picklist picklist) {

    }

    @Override
    public Page<Picklist> findAll(Picklist picklist, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Picklist> findById(Long id) {
        return picklistRepository.findById(id);
    }

    @Override
    public Page<Picklist> findAll(SearchRequest request, Pageable pageable) {
        return picklistRepository.findAll(new Picklist().searchPredicate(request), pageable);
    }

    List<Picklist> findByParentList(Long parentId) {
        return picklistRepository.findByParentList_Id(parentId);
    }

    List<Picklist> findByPickListType(PicklistType picklistType) {
        return picklistRepository.findByPicklistType(picklistType);
    }

    Iterable<Picklist> findByCategoryAndPickListType(Picklist picklist) {
        return picklistRepository.findAll(picklist.predicate());
    }
}
