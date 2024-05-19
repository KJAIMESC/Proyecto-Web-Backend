package com.proyecto1.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.proyecto1.web.dto.usuario_dto;
import com.proyecto1.web.entities.arrendador;
import com.proyecto1.web.entities.arrendatario;
import com.proyecto1.web.repositories.arrendatario_repository;
import com.proyecto1.web.repositories.arrendador_repository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private arrendador_repository arrendadorRepository;

    @Autowired
    private arrendatario_repository arrendatarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ObjectMapper objectMapper = new ObjectMapper();

    public Optional<usuario_dto> autenticar(String correo, String contrasena) {
        Optional<usuario_dto> usuarioDTO = Optional.empty();
        System.out.println("Autenticando usuario con correo: " + correo);
        Optional<arrendador> arrendadorOptional = arrendadorRepository.findByCorreo(correo);
        if (arrendadorOptional.isPresent()) {
            System.out.println("Arrendador encontrado");
            arrendador arrendador = arrendadorOptional.get();
            if (passwordEncoder.matches(contrasena, arrendador.getContrasena())) {
                System.out.println("Contrasena correcta");
                usuarioDTO = Optional.of(new usuario_dto(arrendador.getId_arrendador(), arrendador.getNombres(), arrendador.getApellidos(), arrendador.getCorreo(), arrendador.getContrasena(), "Arrendador"));
            }
        }

        if (!usuarioDTO.isPresent()) {
            Optional<arrendatario> arrendatarioOptional = arrendatarioRepository.findByCorreo(correo);
            if (arrendatarioOptional.isPresent()) {
                System.out.println("Arrendatario encontrado");
                arrendatario arrendatario = arrendatarioOptional.get();
                if (passwordEncoder.matches(contrasena, arrendatario.getContrasena())) {
                    System.out.println("Contrasena correcta");
                    usuarioDTO = Optional.of(new usuario_dto(arrendatario.getId_arrendatario(), arrendatario.getNombres(), arrendatario.getApellidos(), arrendatario.getCorreo(), arrendatario.getContrasena(), "Arrendatario"));
                }
            }
        }

        return usuarioDTO;
    }

    public usuario_dto autorizacion( Authentication authentication ) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("-----------------------");
        System.out.println(  authentication.getName() );
        usuario_dto usuario = objectMapper.readValue(authentication.getName(), usuario_dto.class);
        System.out.println("-----------------------"); 
        return usuario;
    }
}
