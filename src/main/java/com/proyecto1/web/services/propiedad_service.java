package com.proyecto1.web.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto1.web.dto.propiedad_dto;
import com.proyecto1.web.entities.propiedad;
import com.proyecto1.web.repositories.propiedad_repository;

@Service
public class propiedad_service {
    propiedad_repository propiedad_repository;
    ModelMapper modelMapper;

    @Autowired
    public propiedad_service(propiedad_repository propiedad_repository, ModelMapper modelMapper) {
        this.propiedad_repository = propiedad_repository;
        this.modelMapper = modelMapper;
    }

    //GET BY ID
    public propiedad_dto get(Long id) {
        Optional<propiedad> propiedad_optional = propiedad_repository.findById(id);
        propiedad_dto propiedad_dto = null;
        if(propiedad_optional.isPresent()) {
            propiedad propiedad = propiedad_optional.get();
            propiedad_dto = modelMapper.map(propiedad, propiedad_dto.class);
        }
        return propiedad_dto;
    }

    //RETURN PROPIEDAD LIST
    public List<propiedad_dto> getAll( ){
        List<propiedad> propiedadList = (List<propiedad>) propiedad_repository.findAll();
        List<propiedad_dto> propiedad_dtoList = propiedadList.stream().map(propiedad -> modelMapper.map(propiedad, propiedad_dto.class)).collect(Collectors.toList());
        return propiedad_dtoList;
    }

    //SAVE PROPIEDAD
    public propiedad_dto save(propiedad_dto propiedad_dto){
        propiedad propiedad = modelMapper.map(propiedad_dto, propiedad.class);
        propiedad = propiedad_repository.save(propiedad);
        return modelMapper.map(propiedad, propiedad_dto.class);
    }

    //UPDATE PROPIEDAD
    public propiedad_dto update(propiedad_dto propiedad_dto){
        if(propiedad_dto.getId_propiedad() == 0 || !propiedad_repository.existsById(propiedad_dto.getId_propiedad())){
            throw new IllegalArgumentException("No se ha ingresado un ID valido");
        }
        propiedad propiedad = modelMapper.map(propiedad_dto, propiedad.class);
        propiedad = propiedad_repository.save(propiedad);
        return modelMapper.map(propiedad, propiedad_dto.class);
    }

    //DELETE PROPIEDAD BY ID
    public void delete(Long id){
        propiedad_repository.deleteById(id);
    }

}
