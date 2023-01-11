package com.btt.pay.service.impl;

import com.btt.pay.domain.Account;
import com.btt.pay.domain.dto.AccountDTO;
import com.btt.pay.domain.dto.MetadataDTO;
import com.btt.pay.domain.dto.UserDTO;
import com.btt.pay.domain.enumeration.AccountType;
import com.btt.pay.domain.enumeration.ErrorMessage;
import com.btt.pay.payload.request.CreateRetrieveAccountRequest;
import com.btt.pay.payload.response.MessageResponse;
import com.btt.pay.repository.AccountRepository;
import com.btt.pay.service.AccountService;
import com.btt.pay.service.MetadataService;
import com.btt.pay.service.UserService;
import com.btt.pay.service.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    @Value("${app.acct-no-format}")
    private String ACCT_NO_FORMAT;

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final MetadataService metadataService;
    private final UserService userService;

    @Override
    public MessageResponse create(CreateRetrieveAccountRequest request) {
        log.debug("ACCOUNT SERVICE create: {}", request);
        MessageResponse response = new MessageResponse(ErrorMessage.INTERNAL_ERROR.getMessage());

        try {
            AccountDTO newAccountDTO = new AccountDTO();
            MetadataDTO newMetadataDTO = metadataService.create();
            UserDTO userDTO = userService.retrieveById(request.getUserId());

            AccountType accountType = request.getAccountType();

            newAccountDTO.setAccountNumber(generateAccountNumber(accountType));
            newAccountDTO.setAccountType(accountType);
            newAccountDTO.setBalance(BigDecimal.ZERO);
            newAccountDTO.setActive(true);
            newAccountDTO.setMetadata(newMetadataDTO);
            newAccountDTO.setUser(userDTO);

            accountRepository.save(accountMapper.toEntity(newAccountDTO));

            response.setMessage(ErrorMessage.OPEN_SUCCESSFUL.getMessage());
        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public AccountDTO retrieve(CreateRetrieveAccountRequest request) {
        log.debug("ACCOUNT SERVICE retrieve");

        Optional<Account> accountOptional = accountRepository
                .findByAccountTypeAndUserId(request.getAccountType(), request.getUserId());

        return accountOptional.map(accountMapper::toDTO).orElse(null);
    }

    @Override
    public AccountDTO retrieveByAccountNumber(String accountNumber) {
        log.debug("ACCOUNT SERVICE retrieveByAccountNumber");

        Optional<Account> accountOptional = accountRepository.findByAccountNumber(accountNumber);

        return accountOptional.map(accountMapper::toDTO).orElse(null);
    }

    @Override
    public List<AccountDTO> retrieveAllByUserId(Long userId) {
        log.debug("ACCOUNT SERVICE retrieveAllByUserId");

        Optional<List<Account>> accountsOptional = accountRepository.findByUserId(userId);
        List<AccountDTO> accountList = new ArrayList<>();

        if (accountsOptional.isPresent()) {
            accountList = accountsOptional
                    .get()
                    .stream()
                    .map(accountMapper::toDTO)
                    .collect(Collectors.toList());
        }

        return accountList;
    }

    @Override
    public AccountDTO update(AccountDTO accountDTO) {
        log.debug("ACCOUNT SERVICE update: {}", accountDTO.toString());

        Account updatedAccount = accountMapper.toEntity(accountDTO);

        return accountMapper.toDTO(accountRepository.save(updatedAccount));
    }

    @Override
    public boolean isAccountExisting(String accountNumber) {
        log.debug("ACCOUNT SERVICE isAccountExisting: {}", accountNumber);

        return accountRepository.existsByAccountNumber(accountNumber);
    }

    private String generateAccountNumber(AccountType accountType) {
        log.debug("ACCOUNT SERVICE generateAccountNumber");
        String generatedAcctNo =  "";

        switch (accountType) {
            case SAVINGS -> generatedAcctNo = "S";
            default -> generatedAcctNo = "P";
        }

        boolean acctTypeExists = accountRepository.existsByAccountType(accountType);

        if (acctTypeExists) {
            String recentAcctNo = accountRepository.findRecentAccountNumber(accountType);
            long acctNo = Long.parseLong(recentAcctNo.substring(1))  + 1;
            generatedAcctNo = generatedAcctNo + acctNo;
        } else {
            generatedAcctNo = generatedAcctNo.concat(ACCT_NO_FORMAT)
                    .substring(0, ACCT_NO_FORMAT.length() - 1)
                    .concat("1");
        }


        return generatedAcctNo;
    }

}
