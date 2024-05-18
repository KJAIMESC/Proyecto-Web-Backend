package com.proyecto1.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.proyecto1.web.entities.solicitudes;

public interface solicitudes_repository extends CrudRepository<solicitudes, Long>{
    @Query("SELECT p FROM solicitudes p WHERE p.arrendatario.id_arrendatario = ?1")
    List<solicitudes> findAllByAuthenticatedUserId(Long authenticatedUserId);
}
