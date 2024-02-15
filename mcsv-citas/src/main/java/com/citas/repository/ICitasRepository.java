package com.citas.repository;

import com.citas.model.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICitasRepository extends JpaRepository<Cita, Long> {

}
