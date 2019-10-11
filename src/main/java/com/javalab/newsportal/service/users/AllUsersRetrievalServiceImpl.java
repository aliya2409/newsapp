package com.javalab.newsportal.service.users;

import com.javalab.newsportal.dao.UserDAO;
import com.javalab.newsportal.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllUsersRetrievalServiceImpl implements AllUsersRetrievalService {

    private final UserDAO userDAO;

    public AllUsersRetrievalServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> retrieveAll() {
        return userDAO.findAll();
    }
}
