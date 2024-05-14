package com.proyecto1.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class usuario_dto {

    private long id;
    private String nombres;
    private String apellidos;
    private String correo;
    private String contrasena;
}
