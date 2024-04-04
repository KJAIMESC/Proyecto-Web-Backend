package com.proyecto1.web;

import com.proyecto1.web.dto.EstadoSolicitud_dto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EstadoSolicitudDTOTest {
    @Test
    void testConstructorAndGettersSetters() {
        // Arrange
        long idEstadoSolicitud = 30L;
        String estado = "Aceptado";

        // Act
        EstadoSolicitud_dto estadoSolicitudDTO = new EstadoSolicitud_dto();
        estadoSolicitudDTO.setId_EstadoSolicitud(idEstadoSolicitud);
        estadoSolicitudDTO.setEstado(estado);

        // Assert
        assertNotNull(estadoSolicitudDTO);
        assertEquals(idEstadoSolicitud, estadoSolicitudDTO.getId_EstadoSolicitud());
        assertEquals(estado, estadoSolicitudDTO.getEstado());
    }
}
