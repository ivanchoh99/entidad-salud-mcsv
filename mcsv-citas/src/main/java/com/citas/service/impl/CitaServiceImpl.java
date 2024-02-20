package com.citas.service.impl;

import com.citas.client.UsuarioClientREST;
import com.citas.model.Usuario;
import com.citas.model.entity.Cita;
import com.citas.repository.ICitasRepository;
import com.citas.service.ICitaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class CitaServiceImpl implements ICitaService {

    //?     Se puede remplazar cuando se usa lombok por la anotacion que genera el constructor con todos los argumentos
//    private CitaServiceImpl(ICitasRepository citasRepo, UsuarioClientREST usuarioMSVC) {
//        this.citasRepo = citasRepo;
//        this.usuarioMSVC = usuarioMSVC;
//    }

    private final ICitasRepository citasRepo;

    private final UsuarioClientREST usuarioMSVC;

    @Override
    @Transactional(readOnly = true)
    public List<Cita> listar() {
        return citasRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cita> detalle(Long id) {

        Optional<Cita> oCita = citasRepo.findById(id);
        if (oCita.isEmpty()) return Optional.empty();
        Cita cita = oCita.orElseThrow();

        if (cita.getMedicoId() != null) {
            Usuario medico = usuarioMSVC.detalle(cita.getMedicoId());
            cita.setMedicoUsuario(medico);
        }

        if (cita.getPacienteId() != null) {
            Usuario paciente = usuarioMSVC.detalle(cita.getPacienteId());
            cita.setPacienteUsuario(paciente);
        }

        return Optional.of(cita);
    }

    @Override
    @Transactional
    public Cita guardar(Cita cita) {

        if (cita.getMedicoId() != null && cita.getMedicoId() >= 1) {
            Usuario medico = usuarioMSVC.detalle(cita.getMedicoId());
            cita.setMedicoUsuario(medico);
        }

        if (cita.getPacienteId() != null && cita.getPacienteId() >= 1) {
            Usuario paciente = usuarioMSVC.detalle(cita.getPacienteId());
            cita.setPacienteUsuario(paciente);
        }

        return citasRepo.save(cita);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        citasRepo.deleteById(id);
    }


    @Override
    @Transactional
    public Optional<Cita> asignarUsuario(Long citaId, Long usuarioId) {

        Optional<Cita> oCita = citasRepo.findById(citaId);
        if (oCita.isEmpty()) return Optional.empty();

        Usuario usuario = usuarioMSVC.detalle(usuarioId);
        oCita.ifPresent(cita -> {
            if (usuario.getTipoUsuario().equals("medico")) {
                cita.setMedicoId(usuario.getId());
            } else {
                cita.setPacienteId(usuario.getId());
            }
        });
        citasRepo.save(oCita.get());
        log.info("Se a asignado a {} {} {} a la cita {}", usuario.getTipoUsuario(), usuario.getNombre(), usuario.getApellido(), oCita.get().getId());

        return detalle(citaId);
    }
}
