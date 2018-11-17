package com.tlimskech.marketplace.global.picklist;

import com.tlimskech.marketplace.core.data.SearchRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("picklists")
public class PicklistController {

    private final PicklistService picklistService;

    public PicklistController(PicklistService picklistService) {
        this.picklistService = picklistService;
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
}
