package com.tlimskech.marketplace.global.listItem;

import com.tlimskech.marketplace.core.data.SearchRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("listItems")
public class ListItemController {

    private final ListItemService listItemService;

    public ListItemController(ListItemService listItemService) {
        this.listItemService = listItemService;
    }

    @PostMapping("create")
    public ResponseEntity<?> createCategory(@RequestBody ListItem listItem) {
        return ResponseEntity.ok(listItemService.create(listItem));
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody ListItem listItem) {
        return ResponseEntity.ok(listItemService.update(listItem));
    }

    @PostMapping("findAll")
    public ResponseEntity<?> findAll(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(listItemService.findAll(searchRequest, searchRequest.getPaging().getPageRequest()));
    }

    @GetMapping("findByListType/{itemType}")
    public ResponseEntity<?> findByListType(@PathVariable("itemType") String itemType) {
        return ResponseEntity.ok(listItemService.findByItemType(itemType));
    }

    @GetMapping("findByListTypeAndSubcategory/{itemType}/{subCode}")
    public ResponseEntity<?> findByListTypeAndSubcategory(@PathVariable("itemType") String itemType,
                                                                @PathVariable("subCode") String subCode) {
        return ResponseEntity.ok(listItemService.findByItemTypeCategoryAndSubCategory(itemType, subCode));
    }

    @GetMapping("findByListTypeSubcategoryAndParentList/{itemType}/{subCode}/{parentListCode}")
    public ResponseEntity<?> findByListTypeSubcategoryParentListTypeAndParentList(@PathVariable("itemType") String itemType,
                                                          @PathVariable("subCode") String subCode, @PathVariable("parentListCode") String parentListCode) {
        return ResponseEntity.ok(listItemService.findByListTypeSubcategoryAndParentList(itemType, subCode, parentListCode));
    }
}
