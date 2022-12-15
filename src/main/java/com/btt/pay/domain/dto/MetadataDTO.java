package com.btt.pay.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetadataDTO {

    private Long id;
    private Instant dateCreated;
    private Instant dateModified;
    private boolean isDeleted;
}
