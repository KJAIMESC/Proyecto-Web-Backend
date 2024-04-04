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

    private final String message = "El Estado de Solicitud con ID: ";
    private final String noExiste = " no existe";
    private final String noPuedeSEEEERRR = " no existe y por lo tanto no puede ser eliminado";

    @Autowired
    public EstadoSolicitud_service(EstadoSolicitud_repository estadoSolicitud_repository, ModelMapper modelMapper) {
        this.estadoSolicitud_repository = estadoSolicitud_repository;
        this.modelMapper = modelMapper;
    }

    //GET BY ID
    public EstadoSolicitud_dto get(Long id) {
        Optional<EstadoSolicitud> estadoSolicitud_optional = estadoSolicitud_repository.findById(id);
        if (!estadoSolicitud_optional.isPresent()) {
            throw new IllegalArgumentException(message + id + noExiste);
        }
        return modelMapper.map(estadoSolicitud_optional.get(), EstadoSolicitud_dto.class);
    }

    // RETURN ESTADO SOLICITUD LIST
    public List<EstadoSolicitud_dto> getAll() {
        List<EstadoSolicitud> estadoSolicitudList = (List<EstadoSolicitud>) estadoSolicitud_repository.findAll();
        List<EstadoSolicitud_dto> estadoSolicitud_dtoList = estadoSolicitudList.stream()
                .map(estadoSolicitud -> modelMapper.map(estadoSolicitud, EstadoSolicitud_dto.class))
                .toList();
        return estadoSolicitud_dtoList;
    }

    //SAVE ESTADO SOLICITUD
    public EstadoSolicitud_dto save(EstadoSolicitud_dto estadoSolicitud_dto){
        EstadoSolicitud estadoSolicitud = modelMapper.map(estadoSolicitud_dto, EstadoSolicitud.class);
        estadoSolicitud = estadoSolicitud_repository.save(estadoSolicitud);
        return modelMapper.map(estadoSolicitud, EstadoSolicitud_dto.class);
    }

    //UPDATE ESTADO SOLICITUD
    public EstadoSolicitud_dto update(EstadoSolicitud_dto estadoSolicitud_dto) {
        if (!estadoSolicitud_repository.existsById(estadoSolicitud_dto.getId_EstadoSolicitud())) {
            Long id = estadoSolicitud_dto.getId_EstadoSolicitud();
            throw new IllegalArgumentException(message + id + noExiste);
        }
        EstadoSolicitud estadoSolicitud = modelMapper.map(estadoSolicitud_dto, EstadoSolicitud.class);
        estadoSolicitud = estadoSolicitud_repository.save(estadoSolicitud);
        return modelMapper.map(estadoSolicitud, EstadoSolicitud_dto.class);
    }

    //DELETE ESTADO SOLICITUD BY ID
    public void delete(Long id){
        if (!estadoSolicitud_repository.existsById(id)) {
            throw new IllegalArgumentException(message + id + noPuedeSEEEERRR);
        }
        estadoSolicitud_repository.deleteById(id);
    }
}
