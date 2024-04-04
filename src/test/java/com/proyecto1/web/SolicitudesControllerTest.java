package com.proyecto1.web;

import com.proyecto1.web.controllers.solicitudes_controller;
import com.proyecto1.web.dto.solicitudes_dto;
import com.proyecto1.web.services.solicitudes_service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SolicitudesControllerTest {

    @Mock
    private solicitudes_service solicitudesService;

    @InjectMocks
    private solicitudes_controller solicitudesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetSolicitudById() {
        // Arrange
        long id = 1L;
        solicitudes_dto expectedSolicitudDto = new solicitudes_dto();
        when(solicitudesService.get(id)).thenReturn(expectedSolicitudDto);

        // Act
        solicitudes_dto result = solicitudesController.get(id);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testGetAllSolicitudes() {
        // Arrange
        List<solicitudes_dto> expectedSolicitudes = new ArrayList<>();
        expectedSolicitudes.add(new solicitudes_dto());
        expectedSolicitudes.add(new solicitudes_dto());
        when(solicitudesService.getAll()).thenReturn(expectedSolicitudes);

        // Act
        List<solicitudes_dto> result = solicitudesController.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(expectedSolicitudes.size(), result.size());
    }

    @Test
    void testSaveSolicitud() {
        // Arrange
        solicitudes_dto solicitudDtoToSave = new solicitudes_dto();
        when(solicitudesService.save(solicitudDtoToSave)).thenReturn(solicitudDtoToSave);

        // Act
        solicitudes_dto result = solicitudesController.save(solicitudDtoToSave);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testDeleteSolicitud() {
        // Arrange
        long idToDelete = 1L;

        // Act
        solicitudesController.delete(idToDelete);

        // Assert
        verify(solicitudesService, times(1)).delete(idToDelete);
    }
}
