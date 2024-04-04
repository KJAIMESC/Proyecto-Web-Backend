package com.proyecto1.web.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto1.web.dto.arrendatario_dto;
import com.proyecto1.web.entities.arrendatario;
import com.proyecto1.web.repositories.arrendatario_repository;

@Service
public class arrendatario_service {
    arrendatario_repository arrendatario_repository;
    ModelMapper modelMapper;

    private String message = "El arrendatario con ID: ";
    private String noExiste = " no existe";
    private String noPuedeSEEEERRR = " no existe y por lo tanto no puede ser eliminado";
    private String errorContrasena = "La contraseña debe tener al menos 8 caracteres.";

    @Autowired
    public arrendatario_service(arrendatario_repository arrendatario_repository, ModelMapper modelMapper) {
        this.arrendatario_repository = arrendatario_repository;
        this.modelMapper = modelMapper;
    }

    //GET BY ID
    public arrendatario_dto get(Long id) {
        Optional<arrendatario> arrendatario_optional = arrendatario_repository.findById(id);
        //ERROR HANDLING FOR INVALID ID
        if (!arrendatario_optional.isPresent()) {
            throw new IllegalArgumentException(message + id + noExiste);
        }
        return modelMapper.map(arrendatario_optional.get(), arrendatario_dto.class);
    }

    //RETURN ARRENDATARIO LIST
    public List<arrendatario_dto> getAll( ){
        List<arrendatario> arrendatarioList = (List<arrendatario>) arrendatario_repository.findAll();
        List<arrendatario_dto> arrendatario_dtoList = arrendatarioList.stream().map(arrendatario -> modelMapper.map(arrendatario, arrendatario_dto.class)).toList();
        return arrendatario_dtoList;
    }

    //SAVE ARRENDATARIO
    public arrendatario_dto save(arrendatario_dto arrendatario_dto){
        //ERROR HANDLING IF PASSWORD IS TOO SHORT
        if (arrendatario_dto.getContrasena().length() < 8) {
            throw new IllegalArgumentException(errorContrasena);
        }
        arrendatario arrendatario = modelMapper.map(arrendatario_dto, arrendatario.class);
        arrendatario = arrendatario_repository.save(arrendatario);
        return modelMapper.map(arrendatario, arrendatario_dto.class);
    }

    //UPDATE ARRENDATARIO
    public arrendatario_dto update(arrendatario_dto arrendatario_dto){
        //ERROR HANDLING FOR INVALID ID
        if(!arrendatario_repository.existsById(arrendatario_dto.getId_arrendatario())){
            throw new IllegalArgumentException(message + arrendatario_dto.getId_arrendatario() + noExiste);
        }
        //ERROR HANDLING IF PASSWORD IS TOO SHORT
        if (arrendatario_dto.getContrasena().length() < 8) {
            throw new IllegalArgumentException(errorContrasena);
        }
        arrendatario arrendatario = modelMapper.map(arrendatario_dto, arrendatario.class);
        arrendatario = arrendatario_repository.save(arrendatario);
        return modelMapper.map(arrendatario, arrendatario_dto.class);
    }

    //DELETE ARRENDATARIO BY ID
    public void delete(Long id){
        //ERROR HANDLING FOR INVALID ID
        if (!arrendatario_repository.existsById(id)) {
            throw new IllegalArgumentException(message + id + noPuedeSEEEERRR);
        }
        arrendatario_repository.deleteById(id);
    }
}
