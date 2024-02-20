package com.usuarios.repository;

import com.usuarios.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsuarioRepo extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    boolean existsByIdentificacion(String identificacion);

    List<Usuario> findAllByTipoUsuarioEquals(String tipoUsuario);
}
