package com.proyecto1.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class arrendatario_dto {
    private long id_arrendatario;
    private String nombres;
    private String apellidos;
    private String correo;
    private String telefono;
    private String contrasena;
    private boolean activado = false;
}
