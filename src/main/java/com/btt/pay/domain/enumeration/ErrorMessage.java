package com.btt.pay.domain.enumeration;

public enum ErrorMessage {
    // General
    SUCCESSFUL                  ("Successful."),
    INTERNAL_ERROR            ("Sorry, there seems to be an internal problem."),

    // User Login
    INVALID_CREDENTIALS    ("You have entered invalid credentials."),

    // User Registration
    USERNAME_TAKEN          ("Username is already taken."),
    EMAIL_TAKEN                  ("Email is already in use."),
    REGISTER_SUCCESSFUL  ("You have successfully registered.")
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
