package com.proyecto1.web.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto1.web.dto.arrendador_dto;
import com.proyecto1.web.entities.arrendador;
import com.proyecto1.web.repositories.arrendador_repository;

@Service
public class arrendador_service {
    arrendador_repository arrendador_repository;
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    private String message = "El Arrendador con ID: ";
    private String noExiste = " no existe";
    private String noPuedeSEEEERRR = " no existe y por lo tanto no puede ser eliminado";
    private String errorContrasena = "La contraseña debe tener al menos 8 caracteres.";

    @Autowired
    public arrendador_service(arrendador_repository arrendador_repository, ModelMapper modelMapper) {
        this.arrendador_repository = arrendador_repository;
        this.modelMapper = modelMapper;
    }

    //GET BY ID
    public arrendador_dto get(Long id) {
        Optional<arrendador> arrendador_optional = arrendador_repository.findById(id);
        //ERROR HANDLING FOR INVALID ID
        if (!arrendador_optional.isPresent()) {
            throw new IllegalArgumentException(message + id + noExiste);
        }
        arrendador arrendador = arrendador_optional.get();
        return modelMapper.map(arrendador, arrendador_dto.class);
    }

    // RETURN ARRENDADOR LIST
    public List<arrendador_dto> getAll() {
        List<arrendador> arrendadorList = (List<arrendador>) arrendador_repository.findAll();
        List<arrendador_dto> arrendador_dtoList = arrendadorList.stream()
                .map(arrendador -> modelMapper.map(arrendador, arrendador_dto.class))
                .toList();
        return arrendador_dtoList;
    }

    //SAVE ARRENDADOR
    public arrendador_dto save(arrendador_dto arrendador_dto) {
        //ERROR HANDLING IF PASSWORD IS TOO SHORT
        if (arrendador_dto.getContrasena().length() < 8) {
            throw new IllegalArgumentException(errorContrasena);
        }
        String encodedPassword = passwordEncoder.encode(arrendador_dto.getContrasena());
        arrendador_dto.setContrasena(encodedPassword);
        arrendador arrendador = modelMapper.map(arrendador_dto, arrendador.class);
        arrendador = arrendador_repository.save(arrendador);
        return modelMapper.map(arrendador, arrendador_dto.class);
    }

    //UPDATE ARRENDADOR
    public arrendador_dto update(arrendador_dto arrendador_dto) {
        //ERROR HANDLING FOR INVALID ID
        if (!arrendador_repository.existsById(arrendador_dto.getId_arrendador())) {
            throw new IllegalArgumentException(message + arrendador_dto.getId_arrendador() + noExiste);
        }
        //ERROR HANDLING IF PASSWORD IS TOO SHORT
        if (arrendador_dto.getContrasena().length() < 8) {
            throw new IllegalArgumentException(errorContrasena);
        }
        String encodedPassword = passwordEncoder.encode(arrendador_dto.getContrasena());
        arrendador_dto.setContrasena(encodedPassword);
        arrendador arrendador = modelMapper.map(arrendador_dto, arrendador.class);
        arrendador = arrendador_repository.save(arrendador);
        return modelMapper.map(arrendador, arrendador_dto.class);
    }

    //DELETE ARRENDADOR BY ID
    public void delete(Long id) {
        //ERROR HANDLING FOR INVALID ID
        if (!arrendador_repository.existsById(id)) {
            throw new IllegalArgumentException(message + id + noPuedeSEEEERRR);
        }
        arrendador_repository.deleteById(id);
    }

    //UPDATE ARRENDADOR FOR AUTHENTICATED USER
    public arrendador_dto updateForAuthenticatedUser(arrendador_dto arrendador_dto) {
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

            // Fetch the authenticated user
            arrendador existingArrendador = arrendador_repository.findById(authenticatedUserId)
                    .orElseThrow(() -> new IllegalArgumentException(message + authenticatedUserId + noExiste));

            //ERROR HANDLING IF PASSWORD IS TOO SHORT
            if (arrendador_dto.getContrasena().length() < 8) {
                throw new IllegalArgumentException(errorContrasena);
            }

            // Update the existing arrendador with new details
            existingArrendador.setNombres(arrendador_dto.getNombres());
            existingArrendador.setApellidos(arrendador_dto.getApellidos());
            existingArrendador.setCorreo(arrendador_dto.getCorreo());
            existingArrendador.setTelefono(arrendador_dto.getTelefono());
            existingArrendador.setContrasena(passwordEncoder.encode(arrendador_dto.getContrasena()));
            existingArrendador.setActivado(arrendador_dto.isActivado());

            // Save the updated arrendador
            arrendador updatedArrendador = arrendador_repository.save(existingArrendador);
            return modelMapper.map(updatedArrendador, arrendador_dto.class);
        } else {
            throw new IllegalStateException("Unexpected principal type: " + principal.getClass().getName());
        }
    }

    //DELETE ARRENDADOR FOR AUTHENTICATED USER
    public void deleteForAuthenticatedUser() {
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

            //ERROR HANDLING FOR INVALID ID
            if (!arrendador_repository.existsById(authenticatedUserId)) {
                throw new IllegalArgumentException(message + authenticatedUserId + noPuedeSEEEERRR);
            }
            arrendador_repository.deleteById(authenticatedUserId);
        } else {
            throw new IllegalStateException("Unexpected principal type: " + principal.getClass().getName());
        }
    }
}
