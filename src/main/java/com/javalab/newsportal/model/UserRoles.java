package com.javalab.newsportal.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoles implements GrantedAuthority {

    USER, ADMIN, MODERATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
