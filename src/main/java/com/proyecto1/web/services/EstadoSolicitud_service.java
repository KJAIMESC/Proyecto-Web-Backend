package com.proyecto1.web.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto1.web.dto.EstadoSolicitud_dto;
import com.proyecto1.web.entities.EstadoSolicitud;
import com.proyecto1.web.repositories.EstadoSolicitud_repository;

@Service
public class EstadoSolicitud_service {
    EstadoSolicitud_repository estadoSolicitud_repository;
    ModelMapper modelMapper;

    @Autowired
    public EstadoSolicitud_service(EstadoSolicitud_repository estadoSolicitud_repository, ModelMapper modelMapper) {
        this.estadoSolicitud_repository = estadoSolicitud_repository;
        this.modelMapper = modelMapper;
    }

    //GET BY ID
    public EstadoSolicitud_dto get(Long id) {
        Optional<EstadoSolicitud> estadoSolicitud_optional = estadoSolicitud_repository.findById(id);
        EstadoSolicitud_dto estadoSolicitud_dto = null;
        if(estadoSolicitud_optional.isPresent()) {
            EstadoSolicitud estadoSolicitud = estadoSolicitud_optional.get();
            estadoSolicitud_dto = modelMapper.map(estadoSolicitud, EstadoSolicitud_dto.class);
        }
        return estadoSolicitud_dto;
    }

    //RETURN ESTADO SOLICITUD LIST
    public List<EstadoSolicitud_dto> getAll( ){
        List<EstadoSolicitud> estadoSolicitudList = (List<EstadoSolicitud>) estadoSolicitud_repository.findAll();
        List<EstadoSolicitud_dto> estadoSolicitud_dtoList = estadoSolicitudList.stream().map(estadoSolicitud -> modelMapper.map(estadoSolicitud, EstadoSolicitud_dto.class)).collect(Collectors.toList());
        return estadoSolicitud_dtoList;
    }

    //SAVE ESTADO SOLICITUD
    public EstadoSolicitud_dto save(EstadoSolicitud_dto estadoSolicitud_dto){
        EstadoSolicitud estadoSolicitud = modelMapper.map(estadoSolicitud_dto, EstadoSolicitud.class);
        return modelMapper.map(estadoSolicitud, EstadoSolicitud_dto.class);
    }

    //UPDATE ESTADO SOLICITUD
    public EstadoSolicitud_dto update(EstadoSolicitud_dto estadoSolicitud_dto){
        EstadoSolicitud estadoSolicitud = modelMapper.map(estadoSolicitud_dto, EstadoSolicitud.class);
        estadoSolicitud = estadoSolicitud_repository.save(estadoSolicitud);
        return modelMapper.map(estadoSolicitud, EstadoSolicitud_dto.class);
    }

    //DELETE ESTADO SOLICITUD BY ID
    public void delete(Long id){
        estadoSolicitud_repository.deleteById(id);
    }

}
