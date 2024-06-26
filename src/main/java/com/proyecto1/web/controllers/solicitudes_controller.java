package com.proyecto1.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.proyecto1.web.dto.solicitudes_dto;
import com.proyecto1.web.services.solicitudes_service;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/grupo1_6/proyecto1/solicitudes")
public class solicitudes_controller {
    solicitudes_service solicitudes_service;

    @Autowired
    public solicitudes_controller(solicitudes_service solicitudes_service) {
        this.solicitudes_service = solicitudes_service;
    }

    //GET BY ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public solicitudes_dto get(@PathVariable Long id) {
        return solicitudes_service.get(id);
    }

    //RETURN SOLICITUDES LIST
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<solicitudes_dto> getAll( ){
        return solicitudes_service.getAll();
    }

    //SAVE SOLICITUDES
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public solicitudes_dto save(@RequestBody solicitudes_dto solicitudes_dto){
        return solicitudes_service.save(solicitudes_dto);
    }

    //DELETE SOLICITUDES BY ID
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id){
        solicitudes_service.delete(id);
    }

    @GetMapping("/user")
    public List<solicitudes_dto> getAllSolicitudesForAuthenticatedUser() {
        return solicitudes_service.getAllSolicitudesForAuthenticatedUser();
    }

    @GetMapping("/propiedad/{idPropiedad}")
    public List<solicitudes_dto> getAllSolicitudesByPropiedadId(@PathVariable Long idPropiedad) {
        return solicitudes_service.getAllSolicitudesByPropiedadId(idPropiedad);
    }

    @PostMapping("/create")
    public ResponseEntity<solicitudes_dto> createSolicitudForAuthenticatedUser(@RequestBody solicitudes_dto solicitudesDto) {
        solicitudes_dto savedSolicitud = solicitudes_service.saveForAuthenticatedUser(solicitudesDto);
        return new ResponseEntity<>(savedSolicitud, HttpStatus.CREATED);
    }

    // UPDATE CALIFICACION ONLY
    @PutMapping(value = "/updateCalificacion/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<solicitudes_dto> updateCalificacion(@PathVariable Long id, @RequestBody double calificacion) {
        solicitudes_dto updatedSolicitud = solicitudes_service.updateCalificacion(id, calificacion);
        return new ResponseEntity<>(updatedSolicitud, HttpStatus.OK);
    }
}
