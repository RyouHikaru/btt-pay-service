package com.btt.pay.domain.dto;

import com.btt.pay.domain.enumeration.TransactionType;
import com.btt.pay.payload.request.CreateTransactionRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Long id;
    private String transactionNumber;
    private String details;
    private BigDecimal amount;
    private TransactionType transactionType;
    private AccountDTO account;
    private MetadataDTO metadata;

    public TransactionDTO(CreateTransactionRequest request) {
        details = request.getDetails();
        amount = request.getAmount();
        transactionType = request.getTransactionType();
    }

}
