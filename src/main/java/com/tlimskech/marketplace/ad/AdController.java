package com.tlimskech.marketplace.ad;

import com.tlimskech.marketplace.core.data.SearchRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("ads")
@RestController
public class AdController {

    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @PostMapping("listUserAds")
    public ResponseEntity<?> listUserAds(@RequestBody SearchRequest searchRequest) {
        System.out.println(searchRequest.toXmlString());
        return ResponseEntity.ok(adService.findAll(searchRequest));
    }
}
