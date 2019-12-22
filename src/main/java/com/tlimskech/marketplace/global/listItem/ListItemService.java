package com.tlimskech.marketplace.global.listItem;

import com.tlimskech.marketplace.core.data.SearchRequest;
import com.tlimskech.marketplace.core.service.BaseService;
import com.tlimskech.marketplace.exception.ApplicationException;
import com.tlimskech.marketplace.exception.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ListItemService implements BaseService<ListItem, Long> {

    private final ListItemRepository listItemRepository;

    public ListItemService(ListItemRepository listItemRepository) {
        this.listItemRepository = listItemRepository;
    }

    @Override
    public ListItem create(ListItem listItem) {
        if (exists(listItem)) {
            throw new ApplicationException("List Item already exists");
        }
        return listItemRepository.save(listItem);
    }

    @Override
    public ListItem update(ListItem listItem) {
        ListItem found = listItemRepository.findById(listItem.getId()).orElseThrow(() -> new DataNotFoundException("Resource not found"));
        found.copyForUpdate(listItem);
        if (exists(found)) {
            throw new ApplicationException("List Item already exists");
        }
        return listItemRepository.save(found);
    }

    @Override
    public void delete(ListItem listItem) {

    }

    @Override
    public Page<ListItem> findAll(ListItem listItem, Pageable pageable) {
        return null;
    }

    @Override
    public Page<ListItem> findAll(SearchRequest request, Pageable pageable) {
        return listItemRepository.findAll(new ListItem().searchPredicate(request), pageable);
    }

    @Override
    public Optional<ListItem> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean exists(ListItem listItem) {
        if (ObjectUtils.isEmpty(listItem.getId())) {
            return listItemRepository.findByListCode_DataCodeAndItemType(listItem.getListCode().getDataCode(),
                    listItem.getItemType()).isPresent();
        }
        return listItemRepository.findByListCode_DataCodeAndItemTypeAndIdNot(listItem.getListCode().getDataCode(),
                listItem.getItemType(), listItem.getId()).isPresent();
    }

    public List<ListItem> findByItemTypeCategoryAndSubCategory(String itemType, String subcategory) {
        return listItemRepository.findByItemTypeAndSubCategories_Code(ListItemType.valueOf(itemType), subcategory);
    }

    public List<ListItem> findByListTypeSubcategoryAndParentList(String itemType, String subCode, String parentListCode) {
        return listItemRepository.findByItemTypeAndSubCategories_CodeAndParentList_ListCode_DataCode(ListItemType.valueOf(itemType), subCode,
                parentListCode);
    }
}
