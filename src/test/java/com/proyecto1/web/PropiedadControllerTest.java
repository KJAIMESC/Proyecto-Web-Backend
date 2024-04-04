package com.proyecto1.web;

import com.proyecto1.web.controllers.propiedad_controller;
import com.proyecto1.web.dto.propiedad_dto;
import com.proyecto1.web.services.propiedad_service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PropiedadControllerTest {

    @Mock
    private propiedad_service propiedadService;

    @InjectMocks
    private propiedad_controller propiedadController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetPropiedadById() {
        // Arrange
        long id = 1L;
        propiedad_dto expectedPropiedadDto = new propiedad_dto();
        when(propiedadService.get(id)).thenReturn(expectedPropiedadDto);

        // Act
        propiedad_dto result = propiedadController.get(id);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testGetAllPropiedades() {
        // Arrange
        List<propiedad_dto> expectedPropiedades = new ArrayList<>();
        expectedPropiedades.add(new propiedad_dto());
        expectedPropiedades.add(new propiedad_dto());
        when(propiedadService.getAll()).thenReturn(expectedPropiedades);

        // Act
        List<propiedad_dto> result = propiedadController.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(expectedPropiedades.size(), result.size());
    }

    @Test
    void testSavePropiedad() {
        // Arrange
        propiedad_dto propiedadDtoToSave = new propiedad_dto();
        when(propiedadService.save(propiedadDtoToSave)).thenReturn(propiedadDtoToSave);

        // Act
        propiedad_dto result = propiedadController.save(propiedadDtoToSave);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testUpdatePropiedad() {
        // Arrange
        propiedad_dto propiedadDtoToUpdate = new propiedad_dto();
        when(propiedadService.update(propiedadDtoToUpdate)).thenReturn(propiedadDtoToUpdate);

        // Act
        propiedad_dto result = propiedadController.update(propiedadDtoToUpdate);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testDeletePropiedad() {
        // Arrange
        long idToDelete = 1L;

        // Act
        propiedadController.delete(idToDelete);

        // Assert
        verify(propiedadService, times(1)).delete(idToDelete);
    }
}
