package com.btt.pay.domain.enumeration;

public enum ErrorMessage {
    SUCCESSFUL  ("Successful.")
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
