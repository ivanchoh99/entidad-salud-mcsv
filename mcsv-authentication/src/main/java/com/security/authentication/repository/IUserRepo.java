package com.security.authentication.repository;

import com.security.authentication.models.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepo extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);
}
