package com.btt.pay.controller;

import com.btt.pay.domain.enumeration.ErrorMessage;
import com.btt.pay.payload.request.LoginRequest;
import com.btt.pay.payload.request.RegisterRequest;
import com.btt.pay.payload.response.MessageResponse;
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

        if (authService.isUsernameUsed(request.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse(ErrorMessage.USERNAME_TAKEN.getMessage()));
        }

        if (authService.isEmailUsed(request.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse(ErrorMessage.EMAIL_TAKEN.getMessage()));
        }

        return ResponseEntity.ok()
                .body(authService.registerUser(request));
    }

}
