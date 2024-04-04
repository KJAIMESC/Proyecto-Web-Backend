package com.proyecto1.web;

import com.proyecto1.web.dto.propiedad_dto;
import com.proyecto1.web.dto.tipoIngreso_dto;
import com.proyecto1.web.dto.arrendador_dto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PropiedadDTOTest {

    @Test
    void testConstructorAndGettersSetters() {
        // Arrange
        long idPropiedad = 40L;
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
        tipoIngreso_dto tipoIngreso = new tipoIngreso_dto();
        arrendador_dto arrendador = new arrendador_dto();

        // Act
        propiedad_dto propiedadDTO = new propiedad_dto(idPropiedad, imagen, departamento, municipio, nombre, 
                                                        descripcion, cantidadHabitaciones, cantidadBanos, permitidoMascotas, 
                                                        piscina, valorNoche, activado, tipoIngreso, arrendador);

        // Assert
        assertNotNull(propiedadDTO);
        assertEquals(idPropiedad, propiedadDTO.getId_propiedad());
        assertEquals(imagen, propiedadDTO.getImagen());
        assertEquals(departamento, propiedadDTO.getDepartamento());
        assertEquals(municipio, propiedadDTO.getMunicipio());
        assertEquals(nombre, propiedadDTO.getNombre());
        assertEquals(descripcion, propiedadDTO.getDescripcion());
        assertEquals(cantidadHabitaciones, propiedadDTO.getCantitadHabitaciones());
        assertEquals(cantidadBanos, propiedadDTO.getCantidadBanos());
        assertEquals(permitidoMascotas, propiedadDTO.isPermitidoMascotas());
        assertEquals(piscina, propiedadDTO.isPiscina());
        assertEquals(valorNoche, propiedadDTO.getValorNoche());
        assertEquals(activado, propiedadDTO.isActivado());
        assertEquals(tipoIngreso, propiedadDTO.getTipoIngreso());
        assertEquals(arrendador, propiedadDTO.getArrendador());
    }
}
