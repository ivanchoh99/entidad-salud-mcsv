package com.citas.model.entity;

import com.citas.model.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String tipoCita;
    @NotEmpty
    private String estadoCita;
    @NotNull
    @DateTimeFormat
    private Date fechaHora;


    private Long medicoId;//* usuariosID
    private Long pacienteId;//* usuariosID

    //TODO No mapeado
    @Transient
    private Usuario pacienteUsuario;
    @Transient
    private Usuario medicoUsuario;

}
