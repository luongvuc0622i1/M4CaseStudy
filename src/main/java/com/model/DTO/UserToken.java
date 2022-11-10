package com.model.DTO;

import com.model.jwt.AppRole;

import java.util.Set;

public class UserToken {
    private long id;
    private String username;
    private String token;
    private Set<AppRole> roles;

    public UserToken() {
    }

    public UserToken(long id, String username, String token, Set<AppRole> roles) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Set<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AppRole> roles) {
        this.roles = roles;
    }
}
