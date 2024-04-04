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
import com.proyecto1.web.controllers.arrendatario_controller;
import com.proyecto1.web.dto.arrendatario_dto;
import com.proyecto1.web.services.arrendatario_service;

public class arrendatarioControllerTest {

    @Mock
    private arrendatario_service serviceMock;

    @InjectMocks
    private arrendatario_controller controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void whenGetAll_thenReturnListOfArrendatarioDto() {
        // Arrange
        arrendatario_dto dto1 = new arrendatario_dto(1, "Lu Actualizado", "Vargas Gomez", "lu2@javeriana.edu.com", "111111", "qwerty299", false);
        arrendatario_dto dto2 = new arrendatario_dto(5, "Kevin Arrendatario", "Prueba", "k-jaimes@javeriana.edu.com", "111111", "qwerty322", false);
        List<arrendatario_dto> mockedList = Arrays.asList(dto1, dto2);

        when(serviceMock.getAll()).thenReturn(mockedList);

        // Act
        List<arrendatario_dto> resultList = controller.getAll();

        // Assert
        assertEquals(2, resultList.size());
        assertEquals(mockedList, resultList);
    }

    @Test
    void testGetArrendatarioById() {
        // Arrange
        long id = 1L;
        arrendatario_dto expectedDto = new arrendatario_dto();
        when(serviceMock.get(id)).thenReturn(expectedDto);

        // Act
        arrendatario_dto result = controller.get(id);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testSaveArrendatario() {
        // Arrange
        arrendatario_dto arrendatarioDtoToSave = new arrendatario_dto();
        when(serviceMock.save(arrendatarioDtoToSave)).thenReturn(arrendatarioDtoToSave);

        // Act
        arrendatario_dto result = controller.save(arrendatarioDtoToSave);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testUpdateArrendatario() {
        // Arrange
        arrendatario_dto arrendatarioDtoToUpdate = new arrendatario_dto();
        when(serviceMock.update(arrendatarioDtoToUpdate)).thenReturn(arrendatarioDtoToUpdate);

        // Act
        arrendatario_dto result = controller.update(arrendatarioDtoToUpdate);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testDeleteArrendatario() {
        // Arrange
        long idToDelete = 1L;

        // Act
        controller.delete(idToDelete);

        // Assert
        verify(serviceMock, times(1)).delete(idToDelete);
    }

}
