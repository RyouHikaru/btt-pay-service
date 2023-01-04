package com.btt.pay.domain.enumeration;

public enum ErrorMessage {
    // General
    SUCCESSFUL                  ("Successful."),
    INTERNAL_ERROR            ("Sorry, there seems to be an internal problem."),

    // User Login
    INVALID_CREDENTIALS    ("You have entered invalid credentials."),
    LOGOUT_SUCCESSFUL    ("Logout successful."),
    ACCOUNT_LOCKED         ("Your account is temporarily locked due to excessive login attempts."),

    // User Registration
    REGISTER_SUCCESSFUL  ("You have successfully registered."),

    // Accounts
    OPEN_SUCCESSFUL         ("You have successfully open an account."),
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
