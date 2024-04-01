package com.proyecto1.web.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto1.web.dto.solicitudes_dto;
import com.proyecto1.web.entities.solicitudes;
import com.proyecto1.web.repositories.solicitudes_repository;

@Service
public class solicitudes_service {
    solicitudes_repository solicitudes_repository;
    ModelMapper modelMapper;

    @Autowired
    public solicitudes_service(solicitudes_repository solicitudes_repository, ModelMapper modelMapper) {
        this.solicitudes_repository = solicitudes_repository;
        this.modelMapper = modelMapper;
    }

    //GET BY ID
    public solicitudes_dto get(Long id) {
        Optional<solicitudes> solicitudes_optional = solicitudes_repository.findById(id);
        solicitudes_dto solicitudes_dto = null;
        if(solicitudes_optional.isPresent()) {
            solicitudes solicitudes = solicitudes_optional.get();
            solicitudes_dto = modelMapper.map(solicitudes, solicitudes_dto.class);
        }
        return solicitudes_dto;
    }

    //RETURN SOLICITUDES LIST
    public List<solicitudes_dto> getAll( ){
        List<solicitudes> solicitudesList = (List<solicitudes>) solicitudes_repository.findAll();
        List<solicitudes_dto> solicitudes_dtoList = solicitudesList.stream().map(solicitudes -> modelMapper.map(solicitudes, solicitudes_dto.class)).collect(Collectors.toList());
        return solicitudes_dtoList;
    }

    //SAVE SOLICITUDES
    public solicitudes_dto save(solicitudes_dto solicitudes_dto){
        solicitudes solicitudes = modelMapper.map(solicitudes_dto, solicitudes.class);
        solicitudes = solicitudes_repository.save(solicitudes);
        return modelMapper.map(solicitudes, solicitudes_dto.class);
    }

    //UPDATE SOLICITUDES
    public solicitudes_dto update(solicitudes_dto solicitudes_dto){
        if(solicitudes_dto.getId_solicitud() == 0 || !solicitudes_repository.existsById(solicitudes_dto.getId_solicitud())){
            throw new IllegalArgumentException("No se ha ingresado un ID valido");
        }
        solicitudes solicitudes = modelMapper.map(solicitudes_dto, solicitudes.class);
        solicitudes = solicitudes_repository.save(solicitudes);
        return modelMapper.map(solicitudes, solicitudes_dto.class);
    }

    //DELETE SOLICITUDES BY ID
    public void delete(Long id){
        solicitudes_repository.deleteById(id);
    }
}
