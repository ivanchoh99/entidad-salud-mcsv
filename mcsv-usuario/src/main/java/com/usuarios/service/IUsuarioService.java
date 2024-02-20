package com.usuarios.service;


import com.usuarios.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    List<Usuario> listar();

    Optional<Usuario> buscarId(Long id);

    Usuario guardar(Usuario usuario);

    void eliminar(Long id);

    boolean existeCorreo(String email);

    boolean existeId(Long id);

    boolean existeidentificacion(String identificacion);

    List<Usuario> listarMedicos();

    List<Usuario> listarPacientes();
}
