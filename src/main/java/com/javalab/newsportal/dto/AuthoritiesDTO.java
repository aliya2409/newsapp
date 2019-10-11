package com.javalab.newsportal.dto;

import com.javalab.newsportal.model.UserRoles;

import java.util.Set;

public class AuthoritiesDTO {

    private Set<UserRoles> roles;

    public AuthoritiesDTO(Set<UserRoles> roles) {
        this.roles = roles;
    }

    public Set<UserRoles> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRoles> roles) {
        this.roles = roles;
    }
}
