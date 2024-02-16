package com.usuarios.repository;

import com.usuarios.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepo extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByIdentificacion(String idetificacion);

    void deleteByIdentificacion(String identifiacion);

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByIdentificacion(String identificacion);

}
