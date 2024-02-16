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
    private  IUsuarioRepo usuarioRepo;


    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return usuarioRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscar(String identificacion) {
        return usuarioRepo.findByIdentificacion(identificacion);
    }

    @Override
    @Transactional
    public Usuario guardar(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    @Override
    @Transactional
    public void eliminar(String identificacion) {
        usuarioRepo.deleteByIdentificacion(identificacion);
    }

    @Override
    public boolean existeCorreo(String email) {
        return usuarioRepo.existsByEmail(email);
    }

    @Override
    public boolean existeIdentificacion(String identificacion) {
        return usuarioRepo.existsByIdentificacion(identificacion);
    }

    @Override
    public Optional<Usuario> usuarioPorEmail(String email) {
        return usuarioRepo.findByEmail(email);
    }

}
