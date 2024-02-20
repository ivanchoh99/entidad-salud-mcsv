package com.citas.service;

import com.citas.model.entity.Cita;

import java.util.List;
import java.util.Optional;

public interface ICitaService {

    List<Cita> listar();

    Optional<Cita> detalle(Long id);

    Cita guardar(Cita cita);

    void eliminar(Long id);

    Optional<Cita> asignarMedico(Long citaId, Long UsuarioId);

    Optional<Cita> asignarPaciente(Long citaId, Long UsuarioId);
}
