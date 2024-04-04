package com.proyecto1.web;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.proyecto1.web.dto.arrendatario_dto;
import com.proyecto1.web.entities.arrendatario;
import com.proyecto1.web.repositories.arrendatario_repository;
import com.proyecto1.web.services.arrendatario_service;

@SpringBootTest
public class arrendatarioServiceTest {

    @Mock
    private arrendatario_repository arrendatarioRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private arrendatario_service arrendatarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetExistingArrendatario() {
        // Arrange
        Long id = 1L;
        arrendatario arrendatarioEntity = new arrendatario();
        arrendatarioEntity.setId_arrendatario(id);
        arrendatarioEntity.setNombres("Lu Actualizado");
        arrendatarioEntity.setApellidos("Vargas Gomez");
        arrendatarioEntity.setCorreo("lu2@javeriana.edu.com");
        arrendatarioEntity.setTelefono("111111");
        arrendatarioEntity.setContrasena("qwerty299");
        arrendatarioEntity.setActivado(false);

        arrendatario_dto arrendatarioDto = new arrendatario_dto(id, "Lu Actualizado", "Vargas Gomez", "lu2@javeriana.edu.com", "111111", "qwerty299", false);

        when(arrendatarioRepository.findById(id)).thenReturn(Optional.of(arrendatarioEntity));
        when(modelMapper.map(arrendatarioEntity, arrendatario_dto.class)).thenReturn(arrendatarioDto);

        // Act
        arrendatario_dto result = arrendatarioService.get(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId_arrendatario());
        assertEquals("Lu Actualizado", result.getNombres());
        assertEquals("Vargas Gomez", result.getApellidos());
        assertEquals("lu2@javeriana.edu.com", result.getCorreo());
        assertEquals("111111", result.getTelefono());
        assertEquals("qwerty299", result.getContrasena());
        assertFalse(result.isActivado());
    }

    @Test
    public void testGetNonExistingArrendatario() {
        // Arrange
        Long idInexistente = 2L;
        when(arrendatarioRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            arrendatarioService.get(idInexistente);
        });

        assertEquals("El arrendatario con ID: 2 no existe", exception.getMessage());
    }

    
}

