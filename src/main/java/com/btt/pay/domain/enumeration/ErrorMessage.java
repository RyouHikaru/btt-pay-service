package com.btt.pay.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    SUCCESSFUL("Successful."),
    FAILED("Failed.");

    private final String message;
}
