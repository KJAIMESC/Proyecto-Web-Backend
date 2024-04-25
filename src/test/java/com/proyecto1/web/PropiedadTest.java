package com.proyecto1.web;

import org.junit.jupiter.api.Test;

import com.proyecto1.web.entities.arrendador;
import com.proyecto1.web.entities.propiedad;
import com.proyecto1.web.entities.tipoIngreso;

import static org.junit.jupiter.api.Assertions.*;

class PropiedadTest {

    @Test
    void testEntityWithConstructors() {
        byte[] imagen = new byte[]{1, 2, 3};
        String departamento = "Atlántico";
        String municipio = "Barranquilla";
        String nombre = "Casa de playa";
        String descripcion = "Hermosa casa frente al mar";
        int cantidadHabitaciones = 3;
        int cantidadBanos = 2;
        boolean permitidoMascotas = true;
        boolean piscina = true;
        double valorNoche = 150.0;
        boolean activado = true;
        tipoIngreso tipoIngreso = new tipoIngreso();
        arrendador arrendador = new arrendador();

        propiedad propiedad = new propiedad(1L, imagen, departamento, municipio, nombre, descripcion, 
                                            cantidadHabitaciones, cantidadBanos, permitidoMascotas, 
                                            piscina, valorNoche, activado, tipoIngreso, arrendador);

        assertNotNull(propiedad);
        assertEquals(1L, propiedad.getId_propiedad());
        assertEquals(imagen, propiedad.getImagen());
        assertEquals(departamento, propiedad.getDepartamento());
        assertEquals(municipio, propiedad.getMunicipio());
        assertEquals(nombre, propiedad.getNombre());
        assertEquals(descripcion, propiedad.getDescripcion());
        assertEquals(cantidadHabitaciones, propiedad.getCantidadHabitaciones());
        assertEquals(cantidadBanos, propiedad.getCantidadBanos());
        assertEquals(permitidoMascotas, propiedad.isPermitidoMascotas());
        assertEquals(piscina, propiedad.isPiscina());
        assertEquals(valorNoche, propiedad.getValorNoche());
        assertEquals(activado, propiedad.isActivado());
        assertEquals(tipoIngreso, propiedad.getTipoIngreso());
        assertEquals(arrendador, propiedad.getArrendador());
    }

    @Test
    void testEntityWithSettersAndGetters() {
        byte[] imagen = new byte[]{1, 2, 3};
        String departamento = "Atlántico";
        String municipio = "Barranquilla";
        String nombre = "Casa de playa";
        String descripcion = "Hermosa casa frente al mar";
        int cantidadHabitaciones = 3;
        int cantidadBanos = 2;
        boolean permitidoMascotas = true;
        boolean piscina = true;
        double valorNoche = 150.0;
        boolean activado = true;
        tipoIngreso tipoIngreso = new tipoIngreso();
        arrendador arrendador = new arrendador();

        propiedad propiedad = new propiedad();
        propiedad.setId_propiedad(1L);
        propiedad.setImagen(imagen);
        propiedad.setDepartamento(departamento);
        propiedad.setMunicipio(municipio);
        propiedad.setNombre(nombre);
        propiedad.setDescripcion(descripcion);
        propiedad.setCantidadHabitaciones(cantidadHabitaciones);
        propiedad.setCantidadBanos(cantidadBanos);
        propiedad.setPermitidoMascotas(permitidoMascotas);
        propiedad.setPiscina(piscina);
        propiedad.setValorNoche(valorNoche);
        propiedad.setActivado(activado);
        propiedad.setTipoIngreso(tipoIngreso);
        propiedad.setArrendador(arrendador);

        assertNotNull(propiedad);
        assertEquals(1L, propiedad.getId_propiedad());
        assertEquals(imagen, propiedad.getImagen());
        assertEquals(departamento, propiedad.getDepartamento());
        assertEquals(municipio, propiedad.getMunicipio());
        assertEquals(nombre, propiedad.getNombre());
        assertEquals(descripcion, propiedad.getDescripcion());
        assertEquals(cantidadHabitaciones, propiedad.getCantidadHabitaciones());
        assertEquals(cantidadBanos, propiedad.getCantidadBanos());
        assertEquals(permitidoMascotas, propiedad.isPermitidoMascotas());
        assertEquals(piscina, propiedad.isPiscina());
        assertEquals(valorNoche, propiedad.getValorNoche());
        assertEquals(activado, propiedad.isActivado());
        assertEquals(tipoIngreso, propiedad.getTipoIngreso());
        assertEquals(arrendador, propiedad.getArrendador());
    }
}
