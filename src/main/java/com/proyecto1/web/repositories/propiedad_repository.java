package com.proyecto1.web.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.proyecto1.web.entities.propiedad;

public interface propiedad_repository extends CrudRepository<propiedad, Long> {
    @Query("SELECT p FROM propiedad p WHERE p.arrendador.id_arrendador = ?1")
    List<propiedad> findAllByArrendador_IdArrendador(Long idArrendador);
}