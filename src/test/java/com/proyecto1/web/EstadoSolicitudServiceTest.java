package com.proyecto1.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.proyecto1.web.repositories.EstadoSolicitud_repository;
import com.proyecto1.web.services.EstadoSolicitud_service;

@SpringBootTest
public class EstadoSolicitudServiceTest {

    @Mock
    private EstadoSolicitud_repository estadoSolicitud_repository;

    @InjectMocks 
    private EstadoSolicitud_service estadoSolicitud_service;

    @Test
    public void testGetEstadoSolicitudNonExistent() {
        // Arrange
        long nonExistentId = 200L; // ID that does not exist in the database
        when(estadoSolicitud_repository.findById(nonExistentId)).thenReturn(Optional.empty());

        // Act and Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            estadoSolicitud_service.get(nonExistentId);
        });

        String expectedMessage = "El Estado de Solicitud con ID: 200 no existe";
        assertEquals(expectedMessage, exception.getMessage());
    }
}
