package com.proyecto1.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto1.web.dto.arrendador_dto;
import com.proyecto1.web.dto.arrendatario_dto;
import com.proyecto1.web.services.arrendatario_service;

@RestController
@RequestMapping(value = "/api/grupo1_6/proyecto1/arrendatario")
public class arrendatario_controller {
    arrendatario_service arrendatario_service;

    @Autowired
    public arrendatario_controller(arrendatario_service arrendatario_service) {
        this.arrendatario_service = arrendatario_service;
    }

    //GET BY ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public arrendatario_dto get(@PathVariable Long id) {
        return arrendatario_service.get(id);
    }

    //RETURN ARRENDATARIO LIST
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<arrendatario_dto> getAll( ){
        return arrendatario_service.getAll();
    }

    //SAVE ARRENDATARIO
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public arrendatario_dto save(@RequestBody arrendatario_dto arrendatario_dto){
        return arrendatario_service.save(arrendatario_dto);
    }

    //UPDATE ARRENDATARIO
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public arrendatario_dto update(@RequestBody arrendatario_dto arrendatario_dto){
        return arrendatario_service.update(arrendatario_dto);
    }

    //DELETE ARRENDATARIO BY ID
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id){
        arrendatario_service.delete(id);
    }

}
