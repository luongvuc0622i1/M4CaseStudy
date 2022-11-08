package com.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<AppRole> roleSet;

    public AppUser() {
    }

    public AppUser(String name, String password, Set<AppRole> roleSet) {
        this.name = name;
        this.password = password;
        this.roleSet = roleSet;
    }

    public AppUser(Long id, String name, String password, Set<AppRole> roleSet) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.roleSet = roleSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<AppRole> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<AppRole> roleSet) {
        this.roleSet = roleSet;
    }
}
