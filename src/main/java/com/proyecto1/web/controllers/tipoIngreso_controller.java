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

import com.proyecto1.web.dto.tipoIngreso_dto;
import com.proyecto1.web.services.tipoIngreso_service;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/grupo1_6/proyecto1/tipoIngreso")
public class tipoIngreso_controller {
    tipoIngreso_service tipoIngreso_service;

    @Autowired
    public tipoIngreso_controller(tipoIngreso_service tipoIngreso_service) {
        this.tipoIngreso_service = tipoIngreso_service;
    }

    //GET BY ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public tipoIngreso_dto get(@PathVariable Long id) {
        return tipoIngreso_service.get(id);
    }

    //RETURN TIPOINGRESO LIST
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<tipoIngreso_dto> getAll( ){
        return tipoIngreso_service.getAll();
    }

    //SAVE TIPOINGRESO
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public tipoIngreso_dto save(@RequestBody tipoIngreso_dto tipoIngreso_dto){
        return tipoIngreso_service.save(tipoIngreso_dto);
    }

    //UPDATE TIPOINGRESO
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public tipoIngreso_dto update(@RequestBody tipoIngreso_dto tipoIngreso_dto){
        return tipoIngreso_service.update(tipoIngreso_dto);
    }

    //DELETE TIPOINGRESO BY ID
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id){
        tipoIngreso_service.delete(id);
    }
}