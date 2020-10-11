package com.tlimskech.marketplace.auth;

import com.tlimskech.marketplace.auth.data.ResetPasswordRequest;
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

    @GetMapping("/testMail")
    public ResponseEntity<?> testMail() {
        userService.testMail();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/fbLogin")
    public ResponseEntity<?> fbLogin(@RequestParam String code) {
        return ResponseEntity.ok(userService.fbLogin(code));
    }

    @GetMapping("/initiateForgotPassword")
    public ResponseEntity<?> initiateForgotPassword(@RequestParam(value = "email") String email) {
        userService.initiateForgotPassword(email);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/resetUserPassword")
    public ResponseEntity<?> resetUserPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        userService.resetUserPassword(resetPasswordRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
