package com.security.authentication.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Roles {

    @Id
    @Column(name = "role_id")
    private Long roleId;
    private String name;
}
