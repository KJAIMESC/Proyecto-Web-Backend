package com.proyecto1.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto1.web.dto.token_dto;
import com.proyecto1.web.dto.usuario_dto;
import com.proyecto1.web.services.JWTTokenService;

@RestController
@RequestMapping("/jwt/security/autenticar")
public class Autenticacion_controller {

    @Autowired
    private JWTTokenService jwtTokenService;

    @CrossOrigin
    @PostMapping(value = "/generarToken", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public token_dto generarToken(@RequestBody usuario_dto usuarioDTO) {
        return new token_dto(jwtTokenService.generarToken(usuarioDTO), usuarioDTO);
    }

    @CrossOrigin
    @PostMapping(  value = "/autenticar-correo-contrasena", produces = MediaType.APPLICATION_JSON_VALUE)
    public String autenticar( @RequestParam String correo, @RequestParam String contrasena ){
        usuario_dto usuarioDTO = new usuario_dto(3, "Pablo", "Marquez", correo); //TODO: Implementar la busqueda del usuario en la base de datos
        return jwtTokenService.generarToken(usuarioDTO);
    }
}
