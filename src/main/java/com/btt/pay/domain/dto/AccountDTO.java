package com.btt.pay.domain.dto;

import com.btt.pay.domain.enumeration.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private Long id;
    private String accountNumber;
    private AccountType accountType;
    private BigDecimal balance;
    private boolean isActive;
    private MetadataDTO metadata;
    private UserDTO user;

}
