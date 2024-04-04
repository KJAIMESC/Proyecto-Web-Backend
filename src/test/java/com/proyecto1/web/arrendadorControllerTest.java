package com.proyecto1.web;

import com.proyecto1.web.controllers.arrendador_controller;
import com.proyecto1.web.dto.arrendador_dto;
import com.proyecto1.web.services.arrendador_service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class arrendadorControllerTest {

    @Mock
    private arrendador_service arrendadorService;

    @InjectMocks
    private arrendador_controller arrendadorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetArrendadorById() {
        // Arrange
        long id = 1L;
        arrendador_dto expectedArrendadorDto = new arrendador_dto();
        expectedArrendadorDto.setId_arrendador(id);
        when(arrendadorService.get(id)).thenReturn(expectedArrendadorDto);

        // Act
        arrendador_dto result = arrendadorController.get(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId_arrendador());
    }

    @Test
    void testGetAllArrendadores() {
        // Arrange
        List<arrendador_dto> expectedArrendadores = new ArrayList<>();
        expectedArrendadores.add(new arrendador_dto());
        expectedArrendadores.add(new arrendador_dto());
        when(arrendadorService.getAll()).thenReturn(expectedArrendadores);

        // Act
        List<arrendador_dto> result = arrendadorController.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(expectedArrendadores.size(), result.size());
    }

    @Test
    void testSaveArrendador() {
        // Arrange
        arrendador_dto arrendadorDtoToSave = new arrendador_dto();
        when(arrendadorService.save(arrendadorDtoToSave)).thenReturn(arrendadorDtoToSave);

        // Act
        arrendador_dto result = arrendadorController.save(arrendadorDtoToSave);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testUpdateArrendador() {
        // Arrange
        arrendador_dto arrendadorDtoToUpdate = new arrendador_dto();
        when(arrendadorService.update(arrendadorDtoToUpdate)).thenReturn(arrendadorDtoToUpdate);

        // Act
        arrendador_dto result = arrendadorController.update(arrendadorDtoToUpdate);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testDeleteArrendador() {
        // Arrange
        long idToDelete = 1L;

        // Act
        arrendadorController.delete(idToDelete);

        // Assert
        verify(arrendadorService, times(1)).delete(idToDelete);
    }
}
