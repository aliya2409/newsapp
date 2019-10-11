package com.javalab.newsportal.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USERS")
@NamedQuery(name = "User.findUsername", query = "SELECT u.username FROM User u WHERE u.username = :username")
public class User {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String AUTHORITIES = "authorities";
    public static final String ENABLED = "enabled";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Boolean enabled;
    @ElementCollection(targetClass = UserRoles.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "authorities",
            joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "authority")
    @Enumerated(EnumType.ORDINAL)
    private Set<UserRoles> authorities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRoles> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<UserRoles> authorities) {
        this.authorities = authorities;
    }
}
