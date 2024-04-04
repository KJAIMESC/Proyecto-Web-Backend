package com.proyecto1.web;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.proyecto1.web.dto.arrendador_dto;
import com.proyecto1.web.entities.arrendador;
import com.proyecto1.web.repositories.arrendador_repository;
import com.proyecto1.web.services.arrendador_service;

@SpringBootTest
public class arrendadorServiceTest {

    @Mock
    private arrendador_repository arrendadorRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private arrendador_service arrendadorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetExistingArrendador() {
        // Arrange
        Long id = 1L;
        arrendador arrendador = new arrendador();
        arrendador.setId_arrendador(id);
        arrendador.setNombres("Nombre Test");
        arrendador.setApellidos("Apellido Test");
        arrendador.setCorreo("correo@test.com");
        arrendador.setTelefono("12345678");
        arrendador.setContrasena("contraseñaTest");
        arrendador.setActivado(true);

        arrendador_dto arrendadorDto = new arrendador_dto();
        arrendadorDto.setId_arrendador(id);
        arrendadorDto.setNombres("Nombre Test");
        arrendadorDto.setApellidos("Apellido Test");
        arrendadorDto.setCorreo("correo@test.com");
        arrendadorDto.setTelefono("12345678");
        arrendadorDto.setContrasena("contraseñaTest");
        arrendadorDto.setActivado(true);

        when(arrendadorRepository.findById(id)).thenReturn(Optional.of(arrendador));
        when(modelMapper.map(arrendador, arrendador_dto.class)).thenReturn(arrendadorDto);

        // Act
        arrendador_dto result = arrendadorService.get(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId_arrendador());
        assertEquals("Nombre Test", result.getNombres());
        assertEquals("Apellido Test", result.getApellidos());
        assertEquals("correo@test.com", result.getCorreo());
        assertEquals("12345678", result.getTelefono());
        assertEquals("contraseñaTest", result.getContrasena());
        assertTrue(result.isActivado());
    }

    // Additional tests (testGetNonExistingArrendador, testGetAllArrendador, etc.)
}
