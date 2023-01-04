package com.btt.pay.service.mapper;

import com.btt.pay.domain.Account;
import com.btt.pay.domain.dto.AccountDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { UserMapper.class, MetadataMapper.class })
public interface AccountMapper {

    Account toEntity(AccountDTO dto);

    AccountDTO toDTO(Account entity);

    default Account defaultBehavior() {
        return new Account();
    }
}
