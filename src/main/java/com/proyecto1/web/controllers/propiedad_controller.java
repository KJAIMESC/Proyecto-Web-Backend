package com.proyecto1.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto1.web.dto.propiedad_dto;
import com.proyecto1.web.entities.propiedad;
import com.proyecto1.web.services.propiedad_service;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/grupo1_6/proyecto1/propiedad")
public class propiedad_controller {
    propiedad_service propiedad_service;

    @Autowired
    public propiedad_controller(propiedad_service propiedad_service) {
        this.propiedad_service = propiedad_service;
    }

    //GET BY ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public propiedad_dto get(@PathVariable Long id) {
        return propiedad_service.get(id);
    }

    //RETURN PROPIEDAD LIST
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<propiedad_dto> getAll( ){
        return propiedad_service.getAll();
    }

    //SAVE PROPIEDAD
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public propiedad_dto save(@RequestBody propiedad_dto propiedad_dto){
        return propiedad_service.save(propiedad_dto);
    }

    //UPDATE PROPIEDAD
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public propiedad_dto update(@RequestBody propiedad_dto propiedad_dto){
        return propiedad_service.update(propiedad_dto);
    }

    //DELETE PROPIEDAD BY ID
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id){
        propiedad_service.delete(id);
    }

    @GetMapping("/user")
    public List<propiedad_dto> getAllPropertiesForAuthenticatedUser() {
        return propiedad_service.getAllPropertiesForAuthenticatedUser();
    }
}
