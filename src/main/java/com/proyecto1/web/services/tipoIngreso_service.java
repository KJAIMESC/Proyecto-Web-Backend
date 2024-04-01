package com.proyecto1.web.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto1.web.dto.tipoIngreso_dto;
import com.proyecto1.web.entities.tipoIngreso;
import com.proyecto1.web.repositories.tipoIngreso_repository;

@Service
public class tipoIngreso_service {
    tipoIngreso_repository tipoIngreso_repository;
    ModelMapper modelMapper;

    @Autowired
    public tipoIngreso_service(tipoIngreso_repository tipoIngreso_repository, ModelMapper modelMapper) {
        this.tipoIngreso_repository = tipoIngreso_repository;
        this.modelMapper = modelMapper;
    }

    //GET BY ID
    public tipoIngreso_dto get(Long id) {
        Optional<tipoIngreso> tipoIngreso_optional = tipoIngreso_repository.findById(id);
        tipoIngreso_dto tipoIngreso_dto = null;
        if(tipoIngreso_optional.isPresent()) {
            tipoIngreso tipoIngreso = tipoIngreso_optional.get();
            tipoIngreso_dto = modelMapper.map(tipoIngreso, tipoIngreso_dto.class);
        }
        return tipoIngreso_dto;
    }

    //RETURN TIPOINGRESO LIST
    public List<tipoIngreso_dto> getAll( ){
        List<tipoIngreso> tipoIngresoList = (List<tipoIngreso>) tipoIngreso_repository.findAll();
        List<tipoIngreso_dto> tipoIngreso_dtoList = tipoIngresoList.stream().map(tipoIngreso -> modelMapper.map(tipoIngreso, tipoIngreso_dto.class)).toList();
        return tipoIngreso_dtoList;
    }

    //SAVE TIPOINGRESO
    public tipoIngreso_dto save(tipoIngreso_dto tipoIngreso_dto){
        tipoIngreso tipoIngreso = modelMapper.map(tipoIngreso_dto, tipoIngreso.class);
        tipoIngreso = tipoIngreso_repository.save(tipoIngreso);
        return modelMapper.map(tipoIngreso, tipoIngreso_dto.class);
    }

    //UPDATE TIPOINGRESO
    public tipoIngreso_dto update(tipoIngreso_dto tipoIngreso_dto){
        if(tipoIngreso_dto.getId_tipoIngreso() == 0 || !tipoIngreso_repository.existsById(tipoIngreso_dto.getId_tipoIngreso())){
            throw new IllegalArgumentException("No se ha ingresado un ID valido");
        }
        tipoIngreso tipoIngreso = modelMapper.map(tipoIngreso_dto, tipoIngreso.class);
        tipoIngreso = tipoIngreso_repository.save(tipoIngreso);
        return modelMapper.map(tipoIngreso, tipoIngreso_dto.class);
    }

    //DELETE TIPOINGRESO BY ID
    public void delete(Long id){
        tipoIngreso_repository.deleteById(id);
    }
}
