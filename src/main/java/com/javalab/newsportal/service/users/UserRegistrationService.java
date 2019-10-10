package com.javalab.newsportal.service.users;

import com.javalab.newsportal.dto.UserDTO;
import com.javalab.newsportal.model.User;

public interface UserRegistrationService {

    User register(UserDTO user);
}
