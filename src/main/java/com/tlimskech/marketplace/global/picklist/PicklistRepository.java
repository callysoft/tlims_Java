package com.tlimskech.marketplace.global.picklist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface PicklistRepository extends JpaRepository<Picklist, Long>, QuerydslPredicateExecutor<Picklist> {

    List<Picklist> findByParentList_Id(Long parentId);
    List<Picklist> findByPicklistType(PicklistType picklistType);
    List<Picklist> findByPicklistTypeAndCategory_CodeAndSubCategory_Code(PicklistType picklistType, String catCode, String subCatCode);
    List<Picklist> findByPicklistTypeAndCategory_CodeAndSubCategory_CodeAndParentList_PickListcode_DataCode(PicklistType picklistType,
                                                                                                            String catCode, String subCatCode, String parentCode);
}
