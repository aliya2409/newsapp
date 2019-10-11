package com.javalab.newsportal.service.users;

import com.javalab.newsportal.dao.UserDAO;
import com.javalab.newsportal.dao.exception.UserNotFoundException;
import com.javalab.newsportal.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserRetrievalServiceImpl implements UserRetrievalService {
    private final UserDAO userDAO;

    public UserRetrievalServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User retrieve(Long id) {
        return userDAO.findById(id).orElseThrow(() -> new UserNotFoundException("Could not find user with id: " + id));
    }
}
