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

    @PostMapping("declinedAds")
    public ResponseEntity<?> declinedAds(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(adService.declinedAds(searchRequest));
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

    @PostMapping("approveAd")
    public ResponseEntity<?> approve(@RequestBody Ad ad) {
        adService.approve(ad);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("activateOrDeactivate")
    public ResponseEntity<?> activateOrDeactivateAd(@RequestBody Ad ad) {
        System.out.println(ad.toXmlString());
        adService.activateOrDeactivate(ad);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("featuredOrNot")
    public ResponseEntity<?> featuredOrNot(@RequestBody Ad ad) {
        adService.featuredOrNot(ad);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("sponsoredOrNot")
    public ResponseEntity<?> sponsoredOrNot(@RequestBody Ad ad) {
        adService.sponsoredOrNot(ad);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("rejectAd")
    public ResponseEntity<?> rejectAd(@RequestBody Ad ad) {
        adService.reject(ad);
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
        return ResponseEntity.ok(adService.findAdListing(request));
    }

    @GetMapping("categoryCode/{catCode}")
    public ResponseEntity<?> findById(@PathVariable String catCode) {
        return ResponseEntity.ok(adService.groupByPickListCode(catCode));
    }

    @PostMapping("adHistory")
    public ResponseEntity<?> adHistory(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(adService.adHistory(searchRequest));
    }

    @PostMapping("globalSearch")
    public ResponseEntity<?> globalSearch(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(adService.categorizedPredicates(searchRequest));
    }
}
