package com.epam.finalproject.pharmacy.exception;

public class CreateConnectionException extends RuntimeException {
    public CreateConnectionException() {
    }

    public CreateConnectionException(String message) {
        super(message);
    }

    public CreateConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateConnectionException(Throwable e) {
    }

    public CreateConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
