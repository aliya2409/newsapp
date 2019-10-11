package com.javalab.newsportal.service.users;

import com.javalab.newsportal.model.User;
import com.javalab.newsportal.model.UserRoles;

import java.util.Set;

public interface UserAuthoritiesAssignmentService {

    void assignAuthorities(User user, Set<UserRoles> authorities);
}
