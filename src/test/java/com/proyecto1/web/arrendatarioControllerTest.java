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
}
