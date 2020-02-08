package com.tlimskech.marketplace.global.listItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface ListItemRepository extends JpaRepository<ListItem, Long>, QuerydslPredicateExecutor<ListItem> {
    Optional<ListItem> findByListCode_DataCodeAndItemType(String code, ListItemType itemType);
    Optional<ListItem> findByListCode_DataCodeAndItemTypeAndIdNot(String code, ListItemType itemType, Long id);
    List<ListItem> findByItemTypeAndSubCategories_Code(ListItemType itemType, String subCat);
    List<ListItem> findByItemType(ListItemType itemType);
    List<ListItem> findByItemTypeAndSubCategories_CodeAndParentList_ListCode_DataCode(ListItemType itemType, String subCat, String parentListCode);
}
