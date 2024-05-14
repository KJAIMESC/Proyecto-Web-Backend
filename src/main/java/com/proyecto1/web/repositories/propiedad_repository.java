package com.proyecto1.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.proyecto1.web.entities.propiedad;

public interface propiedad_repository extends CrudRepository<propiedad, Long>{
    @Query("SELECT p FROM propiedad p WHERE id_arrendador_fk = ?1")
    List<propiedad> findAllByArrendador_IdArrendadorFk(Long id_arrendador_fk);
}

//TIP DEL PROFE, TRAER DESDE EL ARRENDADOR ENVEZ DE DESDE LA PROPIEDAD
