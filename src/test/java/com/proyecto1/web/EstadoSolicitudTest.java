package com.proyecto1.web;

import org.junit.jupiter.api.Test;

import com.proyecto1.web.entities.EstadoSolicitud;

import static org.junit.jupiter.api.Assertions.*;

class EstadoSolicitudTest {

    @Test
    void testEntityWithConstructors() {
        String estado = "Aprobado";

        EstadoSolicitud estadoSolicitudEntity = new EstadoSolicitud(1L, estado);
        
        assertNotNull(estadoSolicitudEntity);
        assertEquals(1L, estadoSolicitudEntity.getId_EstadoSolicitud());
        assertEquals(estado, estadoSolicitudEntity.getEstado());
    }

    @Test
    void testEntityWithSettersAndGetters() {
        String estado = "Rechazado";

        EstadoSolicitud estadoSolicitudEntity = new EstadoSolicitud();
        estadoSolicitudEntity.setId_EstadoSolicitud(2L);
        estadoSolicitudEntity.setEstado(estado);

        assertNotNull(estadoSolicitudEntity);
        assertEquals(2L, estadoSolicitudEntity.getId_EstadoSolicitud());
        assertEquals(estado, estadoSolicitudEntity.getEstado());
    }
}