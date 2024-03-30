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

import com.proyecto1.web.dto.EstadoSolicitud_dto;
import com.proyecto1.web.services.EstadoSolicitud_service;

@RestController
@RequestMapping(value = "/api/grupo1_6/proyecto1/estadosolicitud")
public class EstadoSolicitud_controller {
    EstadoSolicitud_service estadoSolicitud_service;

    @Autowired
    public EstadoSolicitud_controller(EstadoSolicitud_service estadoSolicitud_service) {
        this.estadoSolicitud_service = estadoSolicitud_service;
    }

    //GET BY ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EstadoSolicitud_dto get(@PathVariable Long id) {
        return estadoSolicitud_service.get(id);
    }

    //RETURN ESTADOSOLICITUD LIST
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EstadoSolicitud_dto> getAll( ){
        return estadoSolicitud_service.getAll();
    }

    //SAVE ESTADOSOLICITUD
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public EstadoSolicitud_dto save(@RequestBody EstadoSolicitud_dto estadoSolicitud_dto){
        return estadoSolicitud_service.save(estadoSolicitud_dto);
    }

    //UPDATE ESTADOSOLICITUD
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public EstadoSolicitud_dto update(@RequestBody EstadoSolicitud_dto estadoSolicitud_dto){
        return estadoSolicitud_service.update(estadoSolicitud_dto);
    }

    //DELETE ESTADOSOLICITUD BY ID
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id){
        estadoSolicitud_service.delete(id);
    }
}
