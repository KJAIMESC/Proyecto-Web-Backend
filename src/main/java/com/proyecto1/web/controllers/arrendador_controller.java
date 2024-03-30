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
import com.proyecto1.web.services.arrendador_service;

@RestController
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
    public List<arrendador_dto> getAll( ){
        return arrendador_service.getAll();
    }

    //SAVE ARRENDADOR
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public arrendador_dto save(@RequestBody arrendador_dto arrendador_dto){
        return arrendador_service.save(arrendador_dto);
    }

    //UPDATE ARRENDADOR
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public arrendador_dto update(@RequestBody arrendador_dto arrendador_dto){
        return arrendador_service.update(arrendador_dto);
    }

    //DELETE ARRENDADOR BY ID
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id){
        arrendador_service.delete(id);
    }

}
