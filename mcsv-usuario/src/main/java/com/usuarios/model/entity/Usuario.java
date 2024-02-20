package com.usuarios.model.entity;

import jakarta.persistence.*;
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
    private Long id;
    @NotEmpty
    @Column(unique = true)
    private String identificacion;
    @NotEmpty
    private String nombre;
    @NotEmpty
    private String apellido;
    @Email
    @NotEmpty
    @Column(unique = true)
    private String email;
    @NotNull
    private Long celular;
    @NotNull
    @DateTimeFormat
    private Date fechaNacimiento;
    @NotNull
    private Character sexo;
    @NotEmpty
    private String tipoUsuario;
}
