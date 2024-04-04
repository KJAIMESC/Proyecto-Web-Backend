package com.proyecto1.web;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.proyecto1.web.controllers.tipoIngreso_controller;
import com.proyecto1.web.dto.tipoIngreso_dto;
import com.proyecto1.web.services.tipoIngreso_service;

public class TipoIngresoServiceTest {

    @Mock
    private tipoIngreso_service tipoIngresoService;

    @InjectMocks
    private tipoIngreso_controller tipoIngresoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenGetTipoIngresoById1_thenReturnTipoIngresoDto() {
        // Arrange
        Long id = 1L;
        tipoIngreso_dto expectedDto = new tipoIngreso_dto(id, "rampa");
        when(tipoIngresoService.get(id)).thenReturn(expectedDto);

        // Act
        tipoIngreso_dto actualDto = tipoIngresoController.get(id);

        // Assert
        assertNotNull(actualDto);
        assertEquals(expectedDto.getId_tipoIngreso(), actualDto.getId_tipoIngreso());
        assertEquals(expectedDto.getTipo(), actualDto.getTipo());
    }

    @Test
    public void whenGetTipoIngresoById2_thenReturnTipoIngresoDto() {
        // Arrange
        Long id = 2L;
        tipoIngreso_dto expectedDto = new tipoIngreso_dto(id, "garageActualizado");
        when(tipoIngresoService.get(id)).thenReturn(expectedDto);

        // Act
        tipoIngreso_dto actualDto = tipoIngresoController.get(id);

        // Assert
        assertNotNull(actualDto);
        assertEquals(expectedDto.getId_tipoIngreso(), actualDto.getId_tipoIngreso());
        assertEquals(expectedDto.getTipo(), actualDto.getTipo());
    }

    @Test
    public void whenGetTipoIngresoById3WhichDoesNotExist_thenThrowException() {
        // Arrange
        Long idInexistente = 3L;
        when(tipoIngresoService.get(idInexistente))
            .thenThrow(new IllegalArgumentException("El tipo de ingreso con ID: 3 no existe"));

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tipoIngresoController.get(idInexistente);
        });

        String expectedMessage = "El tipo de ingreso con ID: 3 no existe";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenGetAllTipoIngreso_thenReturnTipoIngresoList() {
        // Arrange
        tipoIngreso_dto tipoIngreso1 = new tipoIngreso_dto(1L, "rampa");
        tipoIngreso_dto tipoIngreso2 = new tipoIngreso_dto(2L, "garageActualizado");
        List<tipoIngreso_dto> expectedList = Arrays.asList(tipoIngreso1, tipoIngreso2);

        when(tipoIngresoService.getAll()).thenReturn(expectedList);

        // Act
        List<tipoIngreso_dto> actualList = tipoIngresoController.getAll();

        // Assert
        assertNotNull(actualList);
        assertEquals(2, actualList.size());
        assertEquals(expectedList, actualList);
    }

    @Test
    public void whenDeleteTipoIngresoById3WhichDoesNotExist_thenThrowException() {
        // Arrange
        Long idInexistente = 3L;
        doThrow(new IllegalArgumentException("No se ha ingresado un ID valido"))
            .when(tipoIngresoService).delete(idInexistente);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tipoIngresoController.delete(idInexistente);
        });

        String expectedMessage = "No se ha ingresado un ID valido";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
