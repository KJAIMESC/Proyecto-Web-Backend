package com.proyecto1.web.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto1.web.dto.solicitudes_dto;
import com.proyecto1.web.entities.solicitudes;
import com.proyecto1.web.repositories.solicitudes_repository;

import jakarta.transaction.Transactional;

@Service
public class solicitudes_service {

    private solicitudes_repository solicitudes_repository;
    private ModelMapper modelMapper;

    private final String message = "La solicitud con ID: ";
    private final String noExiste = " no existe";
    private final String noPuedeSEEEERRR = " no existe y por lo tanto no puede ser eliminada";

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
            throw new IllegalArgumentException(message + id + noExiste);
        }
        return modelMapper.map(solicitudes_optional.get(), solicitudes_dto.class);
    }

    //RETURN SOLICITUDES LIST
    @Transactional
    public List<solicitudes_dto> getAll( ){
        List<solicitudes> solicitudesList = (List<solicitudes>) solicitudes_repository.findAll();
        return solicitudesList.stream().map(solicitudes -> modelMapper.map(solicitudes, solicitudes_dto.class)).toList();
    }

    //SAVE SOLICITUDES
    @Transactional
    public solicitudes_dto save(solicitudes_dto solicitudes_dto){
        solicitudes solicitudes = modelMapper.map(solicitudes_dto, solicitudes.class);
        solicitudes = solicitudes_repository.save(solicitudes);
        return modelMapper.map(solicitudes, solicitudes_dto.class);
    }

    //DELETE SOLICITUDES BY ID
    @Transactional
    public void delete(Long id){
        if (!solicitudes_repository.existsById(id)) {
            throw new IllegalArgumentException(message + id + noPuedeSEEEERRR);
        }
        solicitudes_repository.deleteById(id);
    }

    @Transactional
    public List<solicitudes_dto> getAllSolicitudesForAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String) {
            String jsonString = (String) principal;
            Long authenticatedUserId = null;
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(jsonString);
                authenticatedUserId = jsonNode.get("id").asLong();
            } catch (Exception e) {
                throw new RuntimeException("Failed to parse JSON string: " + jsonString, e);
            }

            List<solicitudes> solicitudesList = solicitudes_repository.findAllByAuthenticatedUserId(authenticatedUserId);
            return solicitudesList.stream()
                    .map(solicitudes -> modelMapper.map(solicitudes, solicitudes_dto.class))
                    .collect(Collectors.toList());
        } else {
            throw new IllegalStateException("Unexpected principal type: " + principal.getClass().getName());
        }
    }
}
