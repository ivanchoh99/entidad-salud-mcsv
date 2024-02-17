package com.citas.model.entity;

import com.citas.model.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
    private TipoCita tipoCita;

    @NotEmpty
    private EstadoCita estadoCita;

    @NotNull
    @PastOrPresent
    @DateTimeFormat
    private Date fechaHora;

    @NotEmpty
    private Long medicoId;//* usuariosID
    @NotEmpty
    private Long pacienteId;//* usuariosID

    //TODO No mapeado
    @Transient
    private Usuario pacienteUsuario;
    @Transient
    private Usuario medicoUsuario;

}
