package com.proyecto1.web.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto1.web.dto.usuario_dto;



@Service
public class UsuarioService {


    // @Autowired
    // UsuarioRepository usuarioRepository;
    @Autowired
	ModelMapper modelMapper;
    

    public usuario_dto autorizacion( Authentication authentication ) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("-----------------------");
        System.out.println(  authentication.getName() );
        usuario_dto usuario = objectMapper.readValue(authentication.getName(), usuario_dto.class);
        System.out.println("-----------------------"); 
        return usuario;
    }
}
