package com.tlimskech.marketplace.ad;

import com.tlimskech.marketplace.core.data.NTuple;
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

    @PostMapping("pendingAds")
    public ResponseEntity<?> pendingAds(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(adService.pendingAds(searchRequest));
    }

    @PostMapping("featuredAds")
    public ResponseEntity<?> featuredAds(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(adService.featuredAds(searchRequest));
    }

    @PostMapping("archivedAds")
    public ResponseEntity<?> archivedAds(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(adService.archivedAds(searchRequest));
    }

    @PostMapping("sponsoredAds")
    public ResponseEntity<?> sponsoredAds(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(adService.sponsoredAds(searchRequest));
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(adService.findById(id));
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

    @PostMapping("adList")
    public ResponseEntity<?> adList(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(adService.findAllAds(searchRequest));
    }

    @PostMapping("adListAdvance")
    public ResponseEntity<?> adList(@RequestBody Ad ad) {
        return ResponseEntity.ok(adService.findAllAdsAdvance(ad));
    }

    @PostMapping("advanceAdListing")
    public ResponseEntity<?> adListing(@RequestBody NTuple request) {
        System.out.println(request.toXmlString());
        return ResponseEntity.ok(adService.findAdListing(request));
    }

    @GetMapping("categoryCode/{catCode}")
    public ResponseEntity<?> findById(@PathVariable String catCode) {
        return ResponseEntity.ok(adService.groupByPickListCode(catCode));
    }

    @PostMapping("adHistory")
    public ResponseEntity<?> adHistory(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(adService.findAllAds(searchRequest));
    }
}
