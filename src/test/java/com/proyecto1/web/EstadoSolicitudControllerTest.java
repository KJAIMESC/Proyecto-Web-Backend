package com.proyecto1.web;

import com.proyecto1.web.controllers.EstadoSolicitud_controller;
import com.proyecto1.web.dto.EstadoSolicitud_dto;
import com.proyecto1.web.services.EstadoSolicitud_service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EstadoSolicitudControllerTest {

    @Mock
    private EstadoSolicitud_service estadoSolicitudService;

    @InjectMocks
    private EstadoSolicitud_controller estadoSolicitudController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
void testGetEstadoSolicitudById() {
    // Arrange
    long id = 1L;
    EstadoSolicitud_dto expectedEstadoSolicitudDto = new EstadoSolicitud_dto();
    when(estadoSolicitudService.get(id)).thenReturn(expectedEstadoSolicitudDto);

    // Act
    EstadoSolicitud_dto result = estadoSolicitudController.get(id);

    // Assert
    assertNotNull(result);
    // Otras verificaciones que no dependan del ID
}

    @Test
    void testGetAllEstadoSolicitudes() {
        // Arrange
        List<EstadoSolicitud_dto> expectedEstadoSolicitudes = new ArrayList<>();
        expectedEstadoSolicitudes.add(new EstadoSolicitud_dto());
        expectedEstadoSolicitudes.add(new EstadoSolicitud_dto());
        when(estadoSolicitudService.getAll()).thenReturn(expectedEstadoSolicitudes);

        // Act
        List<EstadoSolicitud_dto> result = estadoSolicitudController.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(expectedEstadoSolicitudes.size(), result.size());
    }

    @Test
    void testSaveEstadoSolicitud() {
        // Arrange
        EstadoSolicitud_dto estadoSolicitudDtoToSave = new EstadoSolicitud_dto();
        when(estadoSolicitudService.save(estadoSolicitudDtoToSave)).thenReturn(estadoSolicitudDtoToSave);

        // Act
        EstadoSolicitud_dto result = estadoSolicitudController.save(estadoSolicitudDtoToSave);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testUpdateEstadoSolicitud() {
        // Arrange
        EstadoSolicitud_dto estadoSolicitudDtoToUpdate = new EstadoSolicitud_dto();
        when(estadoSolicitudService.update(estadoSolicitudDtoToUpdate)).thenReturn(estadoSolicitudDtoToUpdate);

        // Act
        EstadoSolicitud_dto result = estadoSolicitudController.update(estadoSolicitudDtoToUpdate);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testDeleteEstadoSolicitud() {
        // Arrange
        long idToDelete = 1L;

        // Act
        estadoSolicitudController.delete(idToDelete);

        // Assert
        verify(estadoSolicitudService, times(1)).delete(idToDelete);
    }
}
