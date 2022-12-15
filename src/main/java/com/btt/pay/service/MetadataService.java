package com.btt.pay.service;

import com.btt.pay.domain.dto.MetadataDTO;

public interface MetadataService {

    MetadataDTO create();
    MetadataDTO retrieve();
    MetadataDTO update();
    String delete();

}
