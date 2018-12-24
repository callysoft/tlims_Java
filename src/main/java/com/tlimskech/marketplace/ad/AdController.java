package com.tlimskech.marketplace.ad;

import com.tlimskech.marketplace.core.data.SearchRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("ads")
@RestController
public class AdController {

    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @PostMapping("listUserAds")
    public ResponseEntity<?> listUserAds(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(adService.findAll(searchRequest));
    }

    @PostMapping("pendingAds")
    public ResponseEntity<?> pedingAds(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(adService.pedingAds(searchRequest));
    }

    @PostMapping("featuredAds")
    public ResponseEntity<?> featuredAds(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(adService.featuredAds(searchRequest));
    }

    @PostMapping("archivedAds")
    public ResponseEntity<?> archivedAds(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(adService.archivedAds(searchRequest));
    }

    @GetMapping("activate/{id}")
    public ResponseEntity<?> activate(@PathVariable Long id) {
        adService.activate(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("deactivate/{id}")
    public ResponseEntity<?> deActivate(@PathVariable Long id) {
        adService.deactivate(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("adList")
    public ResponseEntity<?> adList() {
        return ResponseEntity.ok(adService.findAllAds());
    }
}
