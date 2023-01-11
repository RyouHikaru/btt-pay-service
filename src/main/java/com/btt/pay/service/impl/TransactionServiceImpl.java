package com.btt.pay.service.impl;

import com.btt.pay.domain.Transaction;
import com.btt.pay.domain.dto.AccountDTO;
import com.btt.pay.domain.dto.MetadataDTO;
import com.btt.pay.domain.dto.TransactionDTO;
import com.btt.pay.domain.enumeration.ErrorMessage;
import com.btt.pay.domain.enumeration.TransactionType;
import com.btt.pay.payload.request.CreateTransactionRequest;
import com.btt.pay.payload.response.MessageResponse;
import com.btt.pay.repository.TransactionRepository;
import com.btt.pay.service.AccountService;
import com.btt.pay.service.MetadataService;
import com.btt.pay.service.TransactionService;
import com.btt.pay.service.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    @Value("${app.trans-no-format}")
    private String TRANS_NO_FORMAT;

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountService accountService;
    private final MetadataService metadataService;

    @Override
    public MessageResponse create(CreateTransactionRequest request) {
        log.debug("TRANSACTION SERVICE create: {}", request);
        MessageResponse response = new MessageResponse(ErrorMessage.INTERNAL_ERROR.getMessage());

        try {
            TransactionDTO newTransactionDTO = new TransactionDTO(request);
            MetadataDTO newMetadataDTO = metadataService.create();
            AccountDTO accountDTO = accountService.retrieveByAccountNumber(request.getAccountNumber());

            TransactionType type = request.getTransactionType();
            switch (type) {
                case CREDIT -> {
                    accountDTO.setBalance(accountDTO.getBalance().add(request.getAmount()));
                }
                case DEBIT -> {
                    accountDTO.setBalance(accountDTO.getBalance().subtract(request.getAmount()));
                }
            }

            accountDTO = accountService.update(accountDTO);

            newTransactionDTO.setTransactionNumber(generateTransactionNumber());
            newTransactionDTO.setMetadata(newMetadataDTO);
            newTransactionDTO.setAccount(accountDTO);

            transactionRepository.save(transactionMapper.toEntity(newTransactionDTO));

            response.setMessage(ErrorMessage.SUCCESSFUL.getMessage());
        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public List<TransactionDTO> retrieveAllByAccountNumber(String accountNumber) {
        log.debug("TRANSACTION SERVICE retrieveAllByAccountNumber");

        Optional<List<Transaction>> accountsOptional = transactionRepository.findByAccountNumber(accountNumber);
        List<TransactionDTO> transactionList = new ArrayList<>();

        if (accountsOptional.isPresent()) {
            transactionList = accountsOptional
                    .get()
                    .stream()
                    .map(transactionMapper::toDTO)
                    .collect(Collectors.toList());
        }

        return transactionList;
    }

    private String generateTransactionNumber() {
        log.debug("TRANSACTION SERVICE generateTransactionNumber");
        String generatedTransNo =  TRANS_NO_FORMAT;

        long count = transactionRepository.count();

        if (count != 0) {
            String recentTransNo = transactionRepository.findRecentTransactionNumber();
            long transNo = Long.parseLong(recentTransNo.substring(3))  + 1;
            generatedTransNo = TRANS_NO_FORMAT.substring(0, 3) + transNo;
        } else {
            generatedTransNo = generatedTransNo
                    .substring(0, TRANS_NO_FORMAT.length() - 1)
                    .concat("1");
        }

        return generatedTransNo;
    }

}
