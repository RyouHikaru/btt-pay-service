package com.btt.pay.domain.dto;

import com.btt.pay.domain.enumeration.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Long id;
    private String transactionNumber;
    private String details;
    private TransactionType transactionType;
    private AccountDTO account;
    private MetadataDTO metadata;

}
