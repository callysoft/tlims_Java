package com.tlimskech.marketplace.auth.user;

import com.tlimskech.marketplace.core.data.SearchRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @PostMapping("/findAll")
    public ResponseEntity<?> findAllUser(@RequestBody SearchRequest searchRequest) {
        System.out.println(searchRequest.getPaging().getPageRequest());
        return ResponseEntity.ok(userService.findAll(searchRequest, searchRequest.getPaging().getPageRequest()));
    }
}
