package com.javalab.newsportal.dao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
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
