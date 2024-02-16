package com.usuarios.service;


import com.usuarios.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    List<Usuario> listar();

    Optional<Usuario> buscar(String identificacion);

    Usuario guardar(Usuario usuario);

    void eliminar(String identificacion);

    boolean existeCorreo(String email);

    boolean existeIdentificacion(String identificacion);

    Optional<Usuario> usuarioPorEmail(String email);
}
