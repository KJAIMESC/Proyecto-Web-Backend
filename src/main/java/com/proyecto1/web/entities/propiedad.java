package com.proyecto1.web.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @Column(nullable = false)
    private String departamento;
    @Column(nullable = false)
    private String municipio;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String descripcion;
    @Column(nullable = false)
    private int cantidadHabitaciones;
    @Column(nullable = false)
    private int cantidadBanos;
    @Column(nullable = false)
    private boolean permitidoMascotas;
    @Column(nullable = false)
    private boolean piscina;
    @Column(nullable = false)
    private double valorNoche;
    @Column(nullable = false)
    private boolean activado = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipoIngreso_FK")
    private tipoIngreso tipoIngreso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_arrendador_FK")
    private arrendador arrendador;
}