package com.gmail.etauroginskaya.repository.exception;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable e) {
        super(message, e);
    }
}
