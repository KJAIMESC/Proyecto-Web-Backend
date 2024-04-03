package com.proyecto1.web.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto1.web.dto.solicitudes_dto;
import com.proyecto1.web.entities.solicitudes;
import com.proyecto1.web.repositories.solicitudes_repository;

import jakarta.transaction.Transactional;

@Service
public class solicitudes_service {
    private static final Logger logger = LoggerFactory.getLogger(solicitudes_service.class);

    private solicitudes_repository solicitudes_repository;
    private ModelMapper modelMapper;

    @Autowired
    public solicitudes_service(solicitudes_repository solicitudes_repository, ModelMapper modelMapper) {
        this.solicitudes_repository = solicitudes_repository;
        this.modelMapper = modelMapper;
    }

    //GET BY ID
    @Transactional
    public solicitudes_dto get(Long id) {
        Optional<solicitudes> solicitudes_optional = solicitudes_repository.findById(id);
        if (!solicitudes_optional.isPresent()) {
            throw new IllegalArgumentException("La solicitud con ID: " + id + " no existe");
        }
        return modelMapper.map(solicitudes_optional.get(), solicitudes_dto.class);
    }

    //RETURN SOLICITUDES LIST
    @Transactional
    public List<solicitudes_dto> getAll( ){
        List<solicitudes> solicitudesList = (List<solicitudes>) solicitudes_repository.findAll();
        List<solicitudes_dto> solicitudes_dtoList = solicitudesList.stream().map(solicitudes -> modelMapper.map(solicitudes, solicitudes_dto.class)).collect(Collectors.toList());
        return solicitudes_dtoList;
    }

    //SAVE SOLICITUDES
    @Transactional
    public solicitudes_dto save(solicitudes_dto solicitudes_dto){
        // logger.info(solicitudes_dto.getArrendatario() + "");
        // logger.info(solicitudes_dto.getArrendatario().getNombres() + "");
        solicitudes solicitudes = modelMapper.map(solicitudes_dto, solicitudes.class);
        

        // Log the class name before saving
        logger.info("Class name before saving: " + solicitudes.getClass().getName());

        solicitudes = solicitudes_repository.save(solicitudes);

        // Log the class name after saving
        logger.info("Class name after saving: " + solicitudes.getClass().getName());

        return modelMapper.map(solicitudes, solicitudes_dto.class);
    }
    
    //UPDATE SOLICITUDES
    @Transactional
    public solicitudes_dto update(solicitudes_dto solicitudes_dto){
        if(!solicitudes_repository.existsById(solicitudes_dto.getId_solicitud())){
            throw new IllegalArgumentException("No se ha ingresado un ID valido");
        }
        solicitudes solicitudes = modelMapper.map(solicitudes_dto, solicitudes.class);
        solicitudes = solicitudes_repository.save(solicitudes);
        return modelMapper.map(solicitudes, solicitudes_dto.class);
    }

    //DELETE SOLICITUDES BY ID
    @Transactional
    public void delete(Long id){
        if (!solicitudes_repository.existsById(id)) {
            throw new IllegalArgumentException("La solicitud con ID: " + id + " no existe");
        }
        solicitudes_repository.deleteById(id);
    }
}
