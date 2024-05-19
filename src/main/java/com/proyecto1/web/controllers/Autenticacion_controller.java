package com.proyecto1.web.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto1.web.dto.token_dto;
import com.proyecto1.web.dto.usuario_dto;
import com.proyecto1.web.services.JWTTokenService;
import com.proyecto1.web.services.UsuarioService;

@RestController
@RequestMapping("/jwt/security/autenticar")
public class Autenticacion_controller {

    @Autowired
    private JWTTokenService jwtTokenService;

    @Autowired
    private UsuarioService usuarioService;

    @CrossOrigin
    @PostMapping(value = "/generarToken", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public token_dto generarToken(@RequestBody usuario_dto usuarioDTO) {
        return new token_dto(jwtTokenService.generarToken(usuarioDTO), usuarioDTO, usuarioDTO.getTipo(), usuarioDTO.getNombres(), usuarioDTO.getId());
    }

    @CrossOrigin
    @PostMapping(value = "/autenticar-correo-contrasena", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> autenticar(@RequestBody usuario_dto credentials) {
        String correo = credentials.getCorreo();
        String contrasena = credentials.getContrasena();

        if (correo != null && contrasena != null) {
            // Perform authentication using correo and contrasena
            Optional<usuario_dto> usuarioDTO = usuarioService.autenticar(correo, contrasena);

            if (usuarioDTO.isPresent()) {
                System.out.println("User authentication successful");
                // User authentication successful, generate JWT token
                String token = jwtTokenService.generarToken(usuarioDTO.get());
                token_dto tokenDTO = new token_dto(token, usuarioDTO.get(), usuarioDTO.get().getTipo(), usuarioDTO.get().getNombres(), usuarioDTO.get().getId());
                return ResponseEntity.ok(tokenDTO);
            }
        }

        // User authentication failed
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}
