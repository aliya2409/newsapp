package com.javalab.newsportal.controller;

import com.javalab.newsportal.dao.exception.NoSuchNewsFoundException;
import com.javalab.newsportal.dao.exception.UserNotFoundException;
import com.javalab.newsportal.dao.exception.UsernameExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoSuchNewsFoundException.class})
    public ModelAndView handleNewsNotFound(NoSuchNewsFoundException ex) {
        return new ModelAndView("error", "error", "error.news.notFound");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({UserNotFoundException.class})
    public ModelAndView handleNewsNotFound(UserNotFoundException ex) {
        return new ModelAndView("error", "error", "error.users.notFound");
    }

    @ExceptionHandler({UsernameExistsException.class})
    public ModelAndView handleUsernameExists(UsernameExistsException ex) {
        return new ModelAndView("error", "error", "error.users.usernameExists");
    }


    @ExceptionHandler({Exception.class})
    public ModelAndView handleAll(Exception ex) {
        return new ModelAndView("error", "error", ex.getLocalizedMessage());
    }
}