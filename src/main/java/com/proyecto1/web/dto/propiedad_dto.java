package com.proyecto1.web.dto;

import com.proyecto1.web.entities.arrendador;
import com.proyecto1.web.entities.tipoIngreso;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class propiedad_dto {
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
    private double valorNoche;
    private boolean activado;
    @ManyToOne
    @JoinColumn(name = "id_tipoIngreso_FK")
    private tipoIngreso tipoIngreso;
    @ManyToOne
    @JoinColumn(name = "id_arrendatario_FK")
    private arrendador arrendador;
}
