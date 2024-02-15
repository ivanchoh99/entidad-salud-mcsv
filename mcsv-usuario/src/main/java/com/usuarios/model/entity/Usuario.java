package com.usuarios.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String identificacion;
    @NotEmpty
    private String nombre;
    @NotEmpty
    private String apellido;
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private Date fechaNacimiento;
    @NotEmpty
    private String contrasena;

}
