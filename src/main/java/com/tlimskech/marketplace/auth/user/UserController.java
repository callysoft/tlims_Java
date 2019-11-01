package com.tlimskech.marketplace.auth.user;

import com.tlimskech.marketplace.ad.AdService;
import com.tlimskech.marketplace.auth.data.ChangePasswordRequest;
import com.tlimskech.marketplace.core.data.SearchRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final AdService adService;

    public UserController(UserService userService, AdService adService) {
        this.userService = userService;
        this.adService = adService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.update(user));
    }

    @PostMapping("/updateProfile")
    public ResponseEntity<?> updateProfile(@RequestBody User user) {
        return ResponseEntity.ok(userService.updateProfile(user));
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> updateUser(@RequestBody ChangePasswordRequest passwordRequest) {
        userService.changePassword(passwordRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<?> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @PostMapping("/findAll")
    public ResponseEntity<?> findAllUser(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(userService.findAll(searchRequest, searchRequest.getPaging().getPageRequest()));
    }

    @PostMapping("listUserAds")
    public ResponseEntity<?> listUserAds(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(adService.findAll(searchRequest));
    }

    @GetMapping("getCurrentUserDetails")
    public ResponseEntity<?> getCurrentUserDetails() {
        System.out.println(UserService.getCurrentUser());
        return ResponseEntity.ok(userService.findByUsername(UserService.getCurrentUser()));
    }
}
