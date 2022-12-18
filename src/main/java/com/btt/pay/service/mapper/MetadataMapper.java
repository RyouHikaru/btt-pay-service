package com.btt.pay.service.mapper;

import com.btt.pay.domain.Metadata;
import com.btt.pay.domain.dto.MetadataDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MetadataMapper {

    Metadata toEntity(MetadataDTO dto);

    MetadataDTO toDTO(Metadata entity);

}
