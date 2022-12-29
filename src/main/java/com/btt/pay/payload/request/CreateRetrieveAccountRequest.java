package com.btt.pay.payload.request;

import com.btt.pay.domain.enumeration.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRetrieveAccountRequest {

    private AccountType accountType;
    private Long userId;

    @Override
    public String toString() {
        return "CreateRetrieveAccountRequest{" +
                "accountType=" + accountType +
                ", userId=" + userId +
                '}';
    }
}
