package com.tlimskech.marketplace.global.picklist;

import com.tlimskech.marketplace.core.data.SearchRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("picklists")
public class PicklistController {

    private final PicklistService picklistService;
    private final PicklistRepository picklistRepository;

    public PicklistController(PicklistService picklistService, PicklistRepository picklistRepository) {
        this.picklistService = picklistService;
        this.picklistRepository = picklistRepository;
    }

    @GetMapping("list")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(picklistService.findAll());
    }

    @PostMapping("create")
    public ResponseEntity<?> createCategory(@RequestBody Picklist picklist) {
        return ResponseEntity.ok(picklistService.create(picklist));
    }

    @PostMapping("findAll")
    public ResponseEntity<?> findAll(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(picklistService.findAll(searchRequest, searchRequest.getPaging().getPageRequest()));
    }

    @PostMapping("getListByCategory")
    public ResponseEntity<?> findByCategoryAndPickListType(@RequestBody Picklist picklist) {
        return ResponseEntity.ok(picklistService.findByCategoryAndPickListType(picklist));
    }

    @GetMapping("findSubList/{parentId}")
    public ResponseEntity<?> findByParentCategory(@PathVariable("parentId") Long parentId) {
        return ResponseEntity.ok(picklistService.findByParentList(parentId));
    }

    @GetMapping("findByPickListType/{picklistType}")
    public ResponseEntity<?> findByPickListType(@PathVariable("picklistType") String picklistType) {
        return ResponseEntity.ok(picklistService.findByPickListType(PicklistType.valueOf(picklistType)));
    }

    @GetMapping("findByPickListTypeAndCategory/{picklistType}/{catCode}")
    public ResponseEntity<?> findByPickListTypeAndCategory(@PathVariable("picklistType") String picklistType, @PathVariable("catCode") String catCode) {
        return ResponseEntity.ok(picklistService.findByPickListTypeAndCategory(PicklistType.valueOf(picklistType), catCode));
    }

    @GetMapping("findByListTypeAndCategory/{listType}/{catCode}/{subcatCode}")
    public ResponseEntity<?> findByListTypeAndCategory(@PathVariable("listType") String listType, @PathVariable("catCode") String catCode,
                                                       @PathVariable("subcatCode") String subcatCode) {
        return ResponseEntity.ok(picklistRepository.findByPicklistTypeAndCategory_CodeAndSubCategory_Code(PicklistType.valueOf(listType), catCode, subcatCode));
    }

    @GetMapping("findByListTypeAndCategoryAndParent/{listType}/{catCode}/{subcatCode}/{parentCode}")
    public ResponseEntity<?> findByListTypeAndCategoryAndParent(@PathVariable("listType") String listType, @PathVariable("catCode") String catCode,
                                                       @PathVariable("subcatCode") String subcatCode, @PathVariable("parentCode") String parentCode) {
        return ResponseEntity.ok(picklistService.findByListTypeAndCategoryAndParent(listType, catCode, subcatCode, parentCode));
    }
}
