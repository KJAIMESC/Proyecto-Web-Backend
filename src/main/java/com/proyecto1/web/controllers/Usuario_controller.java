package com.proyecto1.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.proyecto1.web.dto.usuario_dto;
import com.proyecto1.web.services.UsuarioService;

@RestController
@RequestMapping("/jwt/security/usuario")
public class Usuario_controller {

    @Autowired
    UsuarioService usuarioService;

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public usuario_dto autenticar(Authentication authentication) throws Exception{
        System.out.println(authentication);
        return usuarioService.autorizacion(authentication);
    }

    @CrossOrigin
    @GetMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
    public usuario_dto error(Authentication authentication) throws Exception{
        System.out.println(authentication);
        return usuarioService.autorizacion(authentication);
    }
}
