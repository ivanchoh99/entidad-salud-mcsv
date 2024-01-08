package com.security.authentication.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Users {

    @Id
    @Column(name = "user_id")
    private Long userId;
    private String email;
    private String username;
    private String password;
    @Column(name = "birth_date")
    private Date birthDate;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "id_role"),
            inverseJoinColumns = @JoinColumn(name = "id_user"))
    private Set<Roles> roles;
    private Boolean state;
}
