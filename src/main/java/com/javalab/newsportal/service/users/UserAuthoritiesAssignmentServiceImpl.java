package com.javalab.newsportal.service.users;

import com.javalab.newsportal.dao.UserDAO;
import com.javalab.newsportal.model.User;
import com.javalab.newsportal.model.UserRoles;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserAuthoritiesAssignmentServiceImpl implements UserAuthoritiesAssignmentService {

    private final UserDAO userDAO;

    public UserAuthoritiesAssignmentServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void assignAuthorities(User user, Set<UserRoles> authorities) {
        if (authorities.isEmpty()) authorities.add(UserRoles.USER);
        user.setAuthorities(authorities);
        userDAO.save(user);
    }
}
