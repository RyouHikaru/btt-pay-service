package com.btt.pay.controller;

import com.btt.pay.payload.request.CreateRetrieveAccountRequest;
import com.btt.pay.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody CreateRetrieveAccountRequest request) {
        return ResponseEntity.ok()
                .body(accountService.create(request));
    }

    @PostMapping("/user")
    public ResponseEntity<?> retrieveAccountsByUserId(@RequestParam Long id) {
        return ResponseEntity.ok()
                .body(accountService.retrieveAllByUserId(id));
    }

}
