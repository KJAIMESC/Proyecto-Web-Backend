package com.proyecto1.web.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.proyecto1.web.entities.arrendador;

public interface arrendador_repository extends CrudRepository<arrendador, Long>{
    Optional<arrendador> findByCorreo(String correo);
}
