package com.proyecto1.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.proyecto1.web.dto.arrendador_dto;
import com.proyecto1.web.services.arrendador_service;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/grupo1_6/proyecto1/arrendador")
public class arrendador_controller {
    arrendador_service arrendador_service;

    @Autowired
    public arrendador_controller(arrendador_service arrendador_service) {
        this.arrendador_service = arrendador_service;
    }

    //GET BY ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public arrendador_dto get(@PathVariable Long id) {
        return arrendador_service.get(id);
    }

    //RETURN ARRENDADOR LIST
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<arrendador_dto> getAll() {
        return arrendador_service.getAll();
    }

    //SAVE ARRENDADOR
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public arrendador_dto save(@RequestBody arrendador_dto arrendador_dto) {
        return arrendador_service.save(arrendador_dto);
    }

    //UPDATE ARRENDADOR
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public arrendador_dto update(@RequestBody arrendador_dto arrendador_dto) {
        return arrendador_service.update(arrendador_dto);
    }

    //DELETE ARRENDADOR BY ID
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) {
        arrendador_service.delete(id);
    }

    //UPDATE ARRENDADOR FOR AUTHENTICATED USER
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public arrendador_dto updateForAuthenticatedUser(@RequestBody arrendador_dto arrendador_dto) {
        return arrendador_service.updateForAuthenticatedUser(arrendador_dto);
    }

    //DELETE ARRENDADOR FOR AUTHENTICATED USER
    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteForAuthenticatedUser() {
        arrendador_service.deleteForAuthenticatedUser();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
