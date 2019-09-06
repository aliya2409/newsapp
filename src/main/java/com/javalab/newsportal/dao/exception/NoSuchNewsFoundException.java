package com.javalab.newsportal.dao.exception;

public class NoSuchNewsFoundException extends RuntimeException {

    public NoSuchNewsFoundException() {
        super();
    }

    public NoSuchNewsFoundException(String message) {
        super(message);
    }

    public NoSuchNewsFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchNewsFoundException(Throwable cause) {
        super(cause);
    }
}
