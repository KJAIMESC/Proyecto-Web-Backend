package com.proyecto1.web;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.proyecto1.web.controllers.solicitudes_controller;
import com.proyecto1.web.dto.solicitudes_dto;
import com.proyecto1.web.services.solicitudes_service;

public class solicitudServiceTest {

    @Mock
    private solicitudes_service solicitudesService;

    @InjectMocks
    private solicitudes_controller solicitudesController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenGetSolicitudByIdWhichDoesNotExist_thenThrowException() {
        // Arrange
        Long idInexistente = 1L;
        when(solicitudesService.get(idInexistente))
            .thenThrow(new IllegalArgumentException("La solicitud con ID: 1 no existe"));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            solicitudesController.get(idInexistente);
        });

        String expectedMessage = "La solicitud con ID: 1 no existe";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenGetSolicitudById5_thenReturnSolicitudInfo() {
        // Arrange
        Long id = 5L;
        LocalDateTime fecha = LocalDateTime.parse("2024-04-02T14:30:00");
        solicitudes_dto dto = new solicitudes_dto();
        dto.setId_solicitud(id);
        dto.setFechaSolicitud(fecha);
        dto.setHoraSolicitud(fecha);
        dto.setFechaLlegada(fecha);
        dto.setFechaSalida(fecha);
        dto.setValor(500.0);
        dto.setCalificacion(5.0);
        // Configura 'estadoSolicitud', 'arrendatario' y 'propiedad' en dto

        when(solicitudesService.get(id)).thenReturn(dto);

        // Act
        solicitudes_dto result = solicitudesController.get(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId_solicitud());
        assertEquals(fecha, result.getFechaSolicitud());
        // Realiza más verificaciones según sea necesario
    }

    @Test
    public void whenDeleteSolicitudById2WhichDoesNotExist_thenThrowException() {
        // Arrange
        Long idInexistente = 2L;
        doThrow(new IllegalArgumentException("La solicitud con ID: 2 no existe y por lo tanto no puede ser eliminada"))
            .when(solicitudesService).delete(idInexistente);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            solicitudesController.delete(idInexistente);
        });

        String expectedMessage = "La solicitud con ID: 2 no existe y por lo tanto no puede ser eliminada";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
