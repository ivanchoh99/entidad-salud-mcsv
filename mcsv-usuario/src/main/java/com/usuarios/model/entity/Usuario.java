package com.usuarios.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @NotEmpty
    protected String identificacion;
    @NotEmpty
    protected String nombre;
    @NotEmpty
    protected String apellido;
    @Email
    @NotEmpty
    protected String email;
    @NotNull
    @DateTimeFormat
    protected Date fechaNacimiento;
}
