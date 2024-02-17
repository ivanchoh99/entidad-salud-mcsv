package com.citas.model;

import lombok.Data;

import java.util.Date;

@Data
public class Usuario {

    private Long id;
    private String identificacion;
    private String nombre;
    private String apellido;
    private String email;
    private Date fechaNacimiento;


}
