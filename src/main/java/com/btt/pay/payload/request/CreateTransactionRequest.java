package com.btt.pay.payload.request;

import com.btt.pay.domain.enumeration.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransactionRequest {

    private String accountNumber;
    private String details;
    private BigDecimal amount;
    private TransactionType transactionType;

}
