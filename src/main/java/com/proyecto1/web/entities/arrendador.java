package com.proyecto1.web.entities;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class arrendador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_arrendador;
    private String nombres;
    private String apellidos;
    private String correo;
    private String telefono;
    private String contrasena;
    private boolean activado;
}
