package com.proyecto1.web.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto1.web.dto.propiedad_dto;
import com.proyecto1.web.entities.propiedad;
import com.proyecto1.web.repositories.propiedad_repository;

@Service
public class propiedad_service {

    private propiedad_repository propiedad_repository;
    private ModelMapper modelMapper;

    @Autowired
    public propiedad_service(propiedad_repository propiedad_repository, ModelMapper modelMapper) {
        this.propiedad_repository = propiedad_repository;
        this.modelMapper = modelMapper;
    }

    // GET BY ID
    @Transactional
    public propiedad_dto get(Long id) {
        Optional<propiedad> propiedad_optional = propiedad_repository.findById(id);
        // ERROR HANDLING FOR INVALID ID
        if (!propiedad_optional.isPresent()) {
            throw new IllegalArgumentException("La propiedad con ID: " + id + " no existe");
        }

        propiedad propiedadEntity = propiedad_optional.get();
        return modelMapper.map(propiedadEntity, propiedad_dto.class);
    }

    // RETURN PROPIEDAD LIST
    @Transactional
    public List<propiedad_dto> getAll() {
        List<propiedad> propiedadList = (List<propiedad>) propiedad_repository.findAll();
        List<propiedad_dto> propiedad_dtoList = propiedadList.stream().map(propiedad -> modelMapper.map(propiedad, propiedad_dto.class)).collect(Collectors.toList());
        return propiedad_dtoList;
    }

    // SAVE PROPIEDAD
    @Transactional
    public propiedad_dto save(propiedad_dto propiedad_dto) {
        propiedad propiedad = modelMapper.map(propiedad_dto, propiedad.class);
        propiedad = propiedad_repository.save(propiedad);
        return modelMapper.map(propiedad, propiedad_dto.class);
    } 

    // UPDATE PROPIEDAD
    @Transactional
    public propiedad_dto update(propiedad_dto propiedad_dto) {
        // ERROR HANDLING FOR INVALID ID
        if (!propiedad_repository.existsById(propiedad_dto.getId_propiedad())) {
            throw new IllegalArgumentException("La propiedad con ID: " + propiedad_dto.getId_propiedad() + " no existe");
        }
        propiedad propiedad = modelMapper.map(propiedad_dto, propiedad.class);
        propiedad = propiedad_repository.save(propiedad);
        return modelMapper.map(propiedad, propiedad_dto.class);
    }

    // DELETE PROPIEDAD BY ID
    @Transactional
    public void delete(Long id) {
        // ERROR HANDLING FOR INVALID ID
        if (!propiedad_repository.existsById(id)) {
            throw new IllegalArgumentException("La propiedad con ID: " + id + " no existe y por lo tanto no puede ser eliminada");
        }
        propiedad_repository.deleteById(id);
    }
}
