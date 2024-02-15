package com.citas.service;

import com.citas.model.entity.Cita;

import java.util.List;
import java.util.Optional;

public interface ICitaService {

    List<Cita> citas();
    Optional<Cita> detalle(Long id);
    Cita guardar(Cita cita);
    void eliminar(Long id);


}
