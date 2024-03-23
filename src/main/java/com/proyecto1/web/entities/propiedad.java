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
public class propiedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_propiedad;
    private byte[] imagen;
    private String departamento;
    private String municipio;
    private String nombre;
    private String descripcion;
    private int cantitadHabitaciones;
    private int cantidadBanos;
    private boolean permitidoMascotas;
    private boolean piscina;
    private int valorNoche;
    private boolean activado;
}