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
        arrendador_dto arrendador_dto = null;
        if(arrendador_optional.isPresent()) {
            arrendador arrendador = arrendador_optional.get();
            arrendador_dto = modelMapper.map(arrendador, arrendador_dto.class);
        }
        return arrendador_dto;
    }

    //RETURN ARRENDADOR LIST
    public List<arrendador_dto> getAll( ){
        List<arrendador> arrendadorList = (List<arrendador>) arrendador_repository.findAll();
        List<arrendador_dto> arrendador_dtoList = arrendadorList.stream().map(arrendador -> modelMapper.map(arrendador, arrendador_dto.class)).collect(Collectors.toList());
        return arrendador_dtoList;
    }

    //SAVE ARRENDADOR
    public arrendador_dto save(arrendador_dto arrendador_dto){
        arrendador arrendador = modelMapper.map(arrendador_dto, arrendador.class);
        arrendador = arrendador_repository.save(arrendador);
        return modelMapper.map(arrendador, arrendador_dto.class);
    }

    //UPDATE ARRENDADOR
    public arrendador_dto update(arrendador_dto arrendador_dto){
        if(arrendador_dto.getId_arrendador() == 0 || !arrendador_repository.existsById(arrendador_dto.getId_arrendador())){
            throw new IllegalArgumentException("No se ha ingresado un ID valido");
        }
        arrendador arrendador = modelMapper.map(arrendador_dto, arrendador.class);
        arrendador = arrendador_repository.save(arrendador);
        return modelMapper.map(arrendador, arrendador_dto.class);
    }

    //DELETE ARRENDADOR BY ID
    public void delete(Long id){
        arrendador_repository.deleteById(id);
    }
}
