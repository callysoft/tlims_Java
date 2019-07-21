package com.tlimskech.marketplace.auth;

import com.tlimskech.marketplace.auth.user.User;
import com.tlimskech.marketplace.auth.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @GetMapping("/verify/{code}")
    public ResponseEntity<?> verifyUser(@PathVariable String code) {
        userService.verifyUser(code);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
