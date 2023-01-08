package com.btt.pay.controller;

import com.btt.pay.payload.request.CreateTransactionRequest;
import com.btt.pay.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody CreateTransactionRequest request) {
        return ResponseEntity.ok()
                .body(transactionService.create(request));
    }

    @PostMapping("/account")
    public ResponseEntity<?> retrieveAllByAccountNumber(@RequestParam String accountNumber) {
        return ResponseEntity.ok()
                .body(transactionService.retrieveAllByAccountNumber(accountNumber));
    }

}
