package com.proyecto1.web.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class arrendador_dto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_arrendador;
    private String nombres;
    private String apellidos;
    @Column(unique = true)
    private String correo;
    private String telefono;
    private String contrasena;
    @Column(nullable = false)
    private boolean activado = false;
}
