package com.usuarios.service.Implement;

import com.usuarios.model.entity.Usuario;
import com.usuarios.repository.IUsuarioRepo;
import com.usuarios.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepo usuarioRepo;


    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return usuarioRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarId(Long id) {
        return usuarioRepo.findById(id);
    }

    @Override
    @Transactional
    public Usuario guardar(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        usuarioRepo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeCorreo(String email) {
        return usuarioRepo.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeId(Long id) {
        return usuarioRepo.existsById(id);
    }

    @Override
    @Transactional
    public boolean existeidentificacion(String identificacion) {
        return usuarioRepo.existsByIdentificacion(identificacion);
    }

    @Override
    public List<Usuario> listarMedicos() {
        return usuarioRepo.findAllByTipoUsuarioEquals("medico");
    }

    @Override
    public List<Usuario> listarPacientes() {
        return usuarioRepo.findAllByTipoUsuarioEquals("paciente");
    }
}
