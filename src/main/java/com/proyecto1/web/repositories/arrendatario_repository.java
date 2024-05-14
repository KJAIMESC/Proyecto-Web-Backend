package com.proyecto1.web.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.proyecto1.web.entities.arrendatario;

public interface arrendatario_repository extends CrudRepository<arrendatario, Long>{
    Optional<arrendatario> findByCorreo(String correo);
}
