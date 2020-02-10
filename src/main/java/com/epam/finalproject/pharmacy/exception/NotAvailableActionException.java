package com.epam.finalproject.pharmacy.exception;

public class NotAvailableActionException extends Exception {
    public NotAvailableActionException() {
    }

    public NotAvailableActionException(String message) {
        super(message);
    }

    public NotAvailableActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAvailableActionException(Throwable cause) {
        super(cause);
    }

    public NotAvailableActionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
