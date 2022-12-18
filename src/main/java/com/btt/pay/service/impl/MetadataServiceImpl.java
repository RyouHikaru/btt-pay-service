package com.btt.pay.service.impl;

import com.btt.pay.domain.dto.MetadataDTO;
import com.btt.pay.service.MetadataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
public class MetadataServiceImpl implements MetadataService {

    @Override
    public MetadataDTO create() {
        log.debug("METADATA SERVICE create");

        MetadataDTO metadataDTO = new MetadataDTO();
        metadataDTO.setDateCreated(Instant.now());
        metadataDTO.setDeleted(false);

        return metadataDTO;
    }

    @Override
    public MetadataDTO retrieve() {
        log.debug("METADATA SERVICE retrieve");

        return null;
    }

    @Override
    public MetadataDTO update() {
        log.debug("METADATA SERVICE update");

        return null;
    }

    @Override
    public String delete() {
        log.debug("METADATA SERVICE delete");

        return null;
    }
}
