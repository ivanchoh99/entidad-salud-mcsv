package com.citas.service.impl;

import com.citas.model.Usuario;
import com.citas.model.entity.Cita;
import com.citas.repository.ICitasRepository;
import com.citas.service.ICitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImpl implements ICitaService {

    @Autowired
    private ICitasRepository citasRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Cita> listar() {
        return citasRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cita> detalle(Long id) {
        return citasRepo.findById(id);
    }

    @Override
    @Transactional
    public Cita guardar(Cita cita) {
        return citasRepo.save(cita);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        citasRepo.deleteById(id);
    }

    @Override
    public void asignarPaciente(Usuario paciente) {

    }

    @Override
    public void asignarMedico(Usuario medico) {

    }
}
