package com.javalab.newsportal.service.users;

import com.javalab.newsportal.dao.UserDAO;
import org.springframework.stereotype.Service;

@Service
public class UserRemovalServiceImpl implements UserRemovalService {

    private final UserDAO userDAO;

    public UserRemovalServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void removeById(Long id) {
        userDAO.deleteById(id);
    }
}
