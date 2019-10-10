package com.javalab.newsportal.service.users;

import com.javalab.newsportal.dao.UserDAO;
import com.javalab.newsportal.dao.exception.UsernameExistsException;
import com.javalab.newsportal.dto.UserDTO;
import com.javalab.newsportal.model.User;
import com.javalab.newsportal.model.UserRoles;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(UserDTO userDTO) {
        checkUsername(userDTO.getUsername());
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEnabled(true);
        user.setAuthorities(new ArrayList<>());
        user.addAuthority(UserRoles.USER);
        return userDAO.save(user);
    }

    private void checkUsername(String username) {
        if (userDAO.findUsername(username)) {
            throw new UsernameExistsException("There is an account with that username: " + username);
        }
    }
}
