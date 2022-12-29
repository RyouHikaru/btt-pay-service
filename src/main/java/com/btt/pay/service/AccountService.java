package com.btt.pay.service;

import com.btt.pay.domain.dto.AccountDTO;
import com.btt.pay.payload.request.CreateRetrieveAccountRequest;
import com.btt.pay.payload.response.MessageResponse;

import java.util.List;

public interface AccountService {

    MessageResponse create(CreateRetrieveAccountRequest request);

    AccountDTO retrieve(CreateRetrieveAccountRequest request);

    List<AccountDTO> retrieveAllByUserId(Long userId);

}
