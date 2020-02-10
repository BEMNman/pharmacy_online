package com.epam.finalproject.pharmacy.exception;

public class NotFoundItemException extends Exception {
    public NotFoundItemException(String s) {
    }

    public NotFoundItemException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundItemException(Throwable cause) {
        super(cause);
    }

    public NotFoundItemException() {
    }
}
