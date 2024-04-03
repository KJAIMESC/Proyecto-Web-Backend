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
public class propiedad_dto {
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
    private double valorNoche;
    private boolean activado;
    private tipoIngreso_dto tipoIngreso;
    private arrendador_dto arrendador;
}
