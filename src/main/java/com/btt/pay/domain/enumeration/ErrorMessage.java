package com.btt.pay.domain.enumeration;

public enum ErrorMessage {
    // General
    SUCCESSFUL                  ("Successful."),

    // User Login
    INVALID_CREDENTIALS    ("You have entered invalid credentials."),

    // User Registration
    USERNAME_TAKEN          ("Username is already taken."),
    EMAIL_TAKEN                  ("Email is already in use.")
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
