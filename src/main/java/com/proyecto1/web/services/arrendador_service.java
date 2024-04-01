package com.proyecto1.web.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto1.web.dto.arrendador_dto;
import com.proyecto1.web.entities.arrendador;
import com.proyecto1.web.repositories.arrendador_repository;


@Service
public class arrendador_service {
    arrendador_repository arrendador_repository;
    ModelMapper modelMapper;

    @Autowired
    public arrendador_service(arrendador_repository arrendador_repository, ModelMapper modelMapper) {
        this.arrendador_repository = arrendador_repository;
        this.modelMapper = modelMapper;
    }

    //GET BY ID
    public arrendador_dto get(Long id) {
        Optional<arrendador> arrendador_optional = arrendador_repository.findById(id);
        //ERROR HANDLING FOR INVALID ID
        if (!arrendador_optional.isPresent()) {
            throw new IllegalArgumentException("El arrendador con ID: " + id + " no existe");
        }
        arrendador arrendador = arrendador_optional.get();
        return modelMapper.map(arrendador, arrendador_dto.class);
    }

    //RETURN ARRENDADOR LIST
    public List<arrendador_dto> getAll( ){
        List<arrendador> arrendadorList = (List<arrendador>) arrendador_repository.findAll();
        List<arrendador_dto> arrendador_dtoList = arrendadorList.stream().map(arrendador -> modelMapper.map(arrendador, arrendador_dto.class)).collect(Collectors.toList());
        return arrendador_dtoList;
    }

    //SAVE ARRENDADOR
    public arrendador_dto save(arrendador_dto arrendador_dto){
        //ERROR HANDLING IF PASSWORD IS TOO SHORT
        if (arrendador_dto.getContrasena().length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
        }    
        arrendador arrendador = modelMapper.map(arrendador_dto, arrendador.class);
        arrendador = arrendador_repository.save(arrendador);
        return modelMapper.map(arrendador, arrendador_dto.class);
    }

    //UPDATE ARRENDADOR
    public arrendador_dto update(arrendador_dto arrendador_dto){
        //ERROR HANDLING FOR INVALID ID
        if(!arrendador_repository.existsById(arrendador_dto.getId_arrendador())){
            throw new IllegalArgumentException("El arrendador con ID: " + arrendador_dto.getId_arrendador() + " no existe");
        }
        //ERROR HANDLING IF PASSWORD IS TOO SHORT
        if (arrendador_dto.getContrasena().length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
        }
        arrendador arrendador = modelMapper.map(arrendador_dto, arrendador.class);
        arrendador = arrendador_repository.save(arrendador);
        return modelMapper.map(arrendador, arrendador_dto.class);
    }

    //DELETE ARRENDADOR BY ID
    public void delete(Long id){
        //ERROR HANDLING FOR INVALID ID
        if (!arrendador_repository.existsById(id)) {
            throw new IllegalArgumentException("El arrendador con ID: " + id + " no existe y por lo tanto no puede ser eliminado");
        }
        arrendador_repository.deleteById(id);
    }
}
