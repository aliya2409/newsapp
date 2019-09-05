package com.javalab.newsportal.service.news;

public class NoSuchNewsFoundException extends Exception {

    public NoSuchNewsFoundException() {
    }

    public NoSuchNewsFoundException(String message) {
        super(message);
    }

    public NoSuchNewsFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
