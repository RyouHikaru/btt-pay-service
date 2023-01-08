package com.btt.pay.service;

import com.btt.pay.domain.dto.TransactionDTO;
import com.btt.pay.payload.request.CreateTransactionRequest;
import com.btt.pay.payload.response.MessageResponse;

import java.util.List;

public interface TransactionService {

    MessageResponse create(CreateTransactionRequest request);

    List<TransactionDTO> retrieveAllByAccountNumber(String accountNumber);

}
