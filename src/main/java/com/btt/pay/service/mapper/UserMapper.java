package com.btt.pay.service.mapper;

import com.btt.pay.domain.User;
import com.btt.pay.domain.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { MetadataMapper.class })
public interface UserMapper {

    User toEntity(UserDTO dto);

    UserDTO toDTO(User entity);

}
