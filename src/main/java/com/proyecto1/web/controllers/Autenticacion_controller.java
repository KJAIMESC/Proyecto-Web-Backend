package com.proyecto1.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto1.web.dto.usuario_dto;
import com.proyecto1.web.services.JWTTokenService;



@RestController
@RequestMapping(value = "/jwt/security/autenticar")
public class Autenticacion_controller {

    @Autowired
    JWTTokenService jwtTokenService;

    @CrossOrigin
    @PostMapping(  value = "/autenticar", produces = MediaType.APPLICATION_JSON_VALUE)
    public String autenticar( @RequestBody usuario_dto usuario_dto ){
        return JWTTokenService.generarToken(usuario_dto);
    }



}
