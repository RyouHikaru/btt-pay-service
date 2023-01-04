package com.btt.pay.service.mapper;

import com.btt.pay.domain.Transaction;
import com.btt.pay.domain.dto.TransactionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { AccountMapper.class, MetadataMapper.class })
public interface TransactionMapper {

    Transaction toEntity(TransactionDTO dto);

    TransactionDTO toDTO(Transaction entity);

    default Transaction defaultBehavior() {
        return new Transaction();
    }
}
