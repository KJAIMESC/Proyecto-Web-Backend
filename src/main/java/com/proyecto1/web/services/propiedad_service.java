package com.proyecto1.web.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto1.web.dto.propiedad_dto;
import com.proyecto1.web.entities.arrendador;
import com.proyecto1.web.entities.propiedad;
import com.proyecto1.web.repositories.propiedad_repository;

@Service
public class propiedad_service {

    private propiedad_repository propiedad_repository;
    private ModelMapper modelMapper;

    private String message = "La propiedad con ID: ";
    private String noExiste = " no existe";
    private String noPuedeSEEEERRR = " no existe y por lo tanto no puede ser eliminado";

    @Autowired
    public propiedad_service(propiedad_repository propiedad_repository, ModelMapper modelMapper) {
        this.propiedad_repository = propiedad_repository;
        this.modelMapper = modelMapper;
    }

    // GET BY ID
    @Transactional
    public propiedad_dto get(Long id) {
        Optional<propiedad> propiedad_optional = propiedad_repository.findById(id);
        // ERROR HANDLING FOR INVALID ID
        if (!propiedad_optional.isPresent()) {
            throw new IllegalArgumentException(message + id + noExiste);
        }

        propiedad propiedadEntity = propiedad_optional.get();
        return modelMapper.map(propiedadEntity, propiedad_dto.class);
    }

    // RETURN PROPIEDAD LIST
    @Transactional
    public List<propiedad_dto> getAll() {
        List<propiedad> propiedadList = (List<propiedad>) propiedad_repository.findAll();
        List<propiedad_dto> propiedad_dtoList = propiedadList.stream().map(propiedad -> modelMapper.map(propiedad, propiedad_dto.class)).toList();
        return propiedad_dtoList;
    }

    // SAVE PROPIEDAD
    @Transactional
    public propiedad_dto save(propiedad_dto propiedad_dto) {
        propiedad propiedad = modelMapper.map(propiedad_dto, propiedad.class);
        propiedad = propiedad_repository.save(propiedad);
        return modelMapper.map(propiedad, propiedad_dto.class);
    } 

    // UPDATE PROPIEDAD
    @Transactional
    public propiedad_dto update(propiedad_dto propiedad_dto) {
        // ERROR HANDLING FOR INVALID ID
        if (!propiedad_repository.existsById(propiedad_dto.getId_propiedad())) {
            throw new IllegalArgumentException(message + propiedad_dto.getId_propiedad() + noExiste);
        }
        propiedad propiedad = modelMapper.map(propiedad_dto, propiedad.class);
        propiedad = propiedad_repository.save(propiedad);
        return modelMapper.map(propiedad, propiedad_dto.class);
    }

    // DELETE PROPIEDAD BY ID
    @Transactional
    public void delete(Long id) {
        // ERROR HANDLING FOR INVALID ID
        if (!propiedad_repository.existsById(id)) {
            throw new IllegalArgumentException(message + id + noPuedeSEEEERRR);
        }
        propiedad_repository.deleteById(id);
    }

    @Transactional
    public List<propiedad_dto> getAllPropertiesForAuthenticatedUser() {
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

            List<propiedad> propiedadList = propiedad_repository.findAllByArrendador_IdArrendador(authenticatedUserId);
            return propiedadList.stream()
                    .map(propiedad -> modelMapper.map(propiedad, propiedad_dto.class))
                    .collect(Collectors.toList());
        } else {
            throw new IllegalStateException("Unexpected principal type: " + principal.getClass().getName());
        }
    }

    @Transactional
    public propiedad_dto saveForAuthenticatedUser(propiedad_dto propiedadDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String) {
            String jsonString = (String) principal;
            Long authenticatedUserId;
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(jsonString);
                authenticatedUserId = jsonNode.get("id").asLong();
            } catch (Exception e) {
                throw new RuntimeException("Failed to parse JSON string: " + jsonString, e);
            }

            propiedad propiedad = modelMapper.map(propiedadDto, propiedad.class);
            arrendador arrendador = new arrendador();
            arrendador.setId_arrendador(authenticatedUserId);
            propiedad.setArrendador(arrendador);

            propiedad = propiedad_repository.save(propiedad);
            return modelMapper.map(propiedad, propiedad_dto.class);
        } else {
            throw new IllegalStateException("Unexpected principal type: " + principal.getClass().getName());
        }
    }
}
