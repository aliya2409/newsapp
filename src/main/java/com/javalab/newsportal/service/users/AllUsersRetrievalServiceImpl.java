package com.javalab.newsportal.service.users;

import com.javalab.newsportal.dao.UserDAO;
import com.javalab.newsportal.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AllUsersRetrievalServiceImpl implements AllUsersRetrievalService {

    private final UserDAO userDAO;
    private String FOREVER_ADMIN_USERNAME = "admin";

    public AllUsersRetrievalServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> retrieveAll() {
        return userDAO.findAll().stream().filter(user -> !FOREVER_ADMIN_USERNAME.equals(user.getUsername())).collect(Collectors.toList());
    }
}
