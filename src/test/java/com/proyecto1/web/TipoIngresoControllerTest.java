package com.proyecto1.web;

import com.proyecto1.web.controllers.tipoIngreso_controller;
import com.proyecto1.web.dto.tipoIngreso_dto;
import com.proyecto1.web.services.tipoIngreso_service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TipoIngresoControllerTest {

    @Mock
    private tipoIngreso_service tipoIngresoService;

    @InjectMocks
    private tipoIngreso_controller tipoIngresoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetTipoIngresoById() {
        // Arrange
        long id = 1L;
        tipoIngreso_dto expectedTipoIngresoDto = new tipoIngreso_dto();
        when(tipoIngresoService.get(id)).thenReturn(expectedTipoIngresoDto);

        // Act
        tipoIngreso_dto result = tipoIngresoController.get(id);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testGetAllTipoIngresos() {
        // Arrange
        List<tipoIngreso_dto> expectedTipoIngresos = new ArrayList<>();
        expectedTipoIngresos.add(new tipoIngreso_dto());
        expectedTipoIngresos.add(new tipoIngreso_dto());
        when(tipoIngresoService.getAll()).thenReturn(expectedTipoIngresos);

        // Act
        List<tipoIngreso_dto> result = tipoIngresoController.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(expectedTipoIngresos.size(), result.size());
    }

    @Test
    void testSaveTipoIngreso() {
        // Arrange
        tipoIngreso_dto tipoIngresoDtoToSave = new tipoIngreso_dto();
        when(tipoIngresoService.save(tipoIngresoDtoToSave)).thenReturn(tipoIngresoDtoToSave);

        // Act
        tipoIngreso_dto result = tipoIngresoController.save(tipoIngresoDtoToSave);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testUpdateTipoIngreso() {
        // Arrange
        tipoIngreso_dto tipoIngresoDtoToUpdate = new tipoIngreso_dto();
        when(tipoIngresoService.update(tipoIngresoDtoToUpdate)).thenReturn(tipoIngresoDtoToUpdate);

        // Act
        tipoIngreso_dto result = tipoIngresoController.update(tipoIngresoDtoToUpdate);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testDeleteTipoIngreso() {
        // Arrange
        long idToDelete = 1L;

        // Act
        tipoIngresoController.delete(idToDelete);

        // Assert
        verify(tipoIngresoService, times(1)).delete(idToDelete);
    }
}
