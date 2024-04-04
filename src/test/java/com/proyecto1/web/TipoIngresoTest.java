package com.proyecto1.web;

import org.junit.jupiter.api.Test;

import com.proyecto1.web.entities.tipoIngreso;

import static org.junit.jupiter.api.Assertions.*;

class TipoIngresoTest {

    @Test
    void testEntityWithConstructors() {
        String tipo = "Ingreso mensual";

        tipoIngreso tipoIngresoEntity = new tipoIngreso(1L, tipo);
        
        assertNotNull(tipoIngresoEntity);
        assertEquals(1L, tipoIngresoEntity.getId_tipoIngreso());
        assertEquals(tipo, tipoIngresoEntity.getTipo());
    }

    @Test
    void testEntityWithSettersAndGetters() {
        String tipo = "Ingreso anual";

        tipoIngreso tipoIngresoEntity = new tipoIngreso();
        tipoIngresoEntity.setId_tipoIngreso(2L);
        tipoIngresoEntity.setTipo(tipo);

        assertNotNull(tipoIngresoEntity);
        assertEquals(2L, tipoIngresoEntity.getId_tipoIngreso());
        assertEquals(tipo, tipoIngresoEntity.getTipo());
    }
}
