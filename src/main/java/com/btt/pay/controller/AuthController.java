package com.btt.pay.controller;

import com.btt.pay.payload.request.LoginRequest;
import com.btt.pay.payload.request.RegisterRequest;
import com.btt.pay.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok()
                .body(authService.authenticateUser(request));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok()
                .body(authService.registerUser(request));
    }

    @PostMapping("/register/validate-username")
    public ResponseEntity<Boolean> isUsernameUsed(@RequestParam String username) {
        return ResponseEntity.ok()
                .body(authService.isUsernameUsed(username));
    }

    @PostMapping("/register/validate-email")
    public ResponseEntity<Boolean> isEmailUsed(@RequestParam String email) {
        return ResponseEntity.ok()
                .body(authService.isEmailUsed(email));
    }

}
