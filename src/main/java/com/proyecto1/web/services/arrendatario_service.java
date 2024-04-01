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

    @Autowired
    public arrendatario_service(arrendatario_repository arrendatario_repository, ModelMapper modelMapper) {
        this.arrendatario_repository = arrendatario_repository;
        this.modelMapper = modelMapper;
    }

    //GET BY ID
    public arrendatario_dto get(Long id) {
        Optional<arrendatario> arrendatario_optional = arrendatario_repository.findById(id);
        arrendatario_dto arrendatario_dto = null;
        if(arrendatario_optional.isPresent()) {
            arrendatario arrendatario = arrendatario_optional.get();
            arrendatario_dto = modelMapper.map(arrendatario, arrendatario_dto.class);
        }
        return arrendatario_dto;
    }

    //RETURN ARRENDATARIO LIST
    public List<arrendatario_dto> getAll( ){
        List<arrendatario> arrendatarioList = (List<arrendatario>) arrendatario_repository.findAll();
        List<arrendatario_dto> arrendatario_dtoList = arrendatarioList.stream().map(arrendatario -> modelMapper.map(arrendatario, arrendatario_dto.class)).collect(Collectors.toList());
        return arrendatario_dtoList;
    }

    //SAVE ARRENDATARIO
    public arrendatario_dto save(arrendatario_dto arrendatario_dto){
        arrendatario arrendatario = modelMapper.map(arrendatario_dto, arrendatario.class);
        arrendatario = arrendatario_repository.save(arrendatario);
        return modelMapper.map(arrendatario, arrendatario_dto.class);
    }

    //UPDATE ARRENDATARIO
    public arrendatario_dto update(arrendatario_dto arrendatario_dto){
        if(arrendatario_dto.getId_arrendatario() == 0 || !arrendatario_repository.existsById(arrendatario_dto.getId_arrendatario())){
            throw new IllegalArgumentException("No se ha ingresado un ID valido");
        }
        arrendatario arrendatario = modelMapper.map(arrendatario_dto, arrendatario.class);
        arrendatario = arrendatario_repository.save(arrendatario);
        return modelMapper.map(arrendatario, arrendatario_dto.class);
    }

    //DELETE ARRENDATARIO BY ID
    public void delete(Long id){
        arrendatario_repository.deleteById(id);
    }
}
