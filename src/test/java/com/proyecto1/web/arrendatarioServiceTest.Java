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

    @Test
    public void testGetAllArrendatarios() {
        // Arrange
        List<arrendatario> arrendatarios = Arrays.asList(
            new arrendatario(1L, "Lu Actualizado", "Vargas Gomez", "lu2@javeriana.edu.com", "111111", "qwerty299", false),
            new arrendatario(5L, "Kevin Arrendatario", "Prueba", "k-jaimes@javeriana.edu.com", "111111", "qwerty322", false)
        );
        when(arrendatarioRepository.findAll()).thenReturn(arrendatarios);

        arrendatario_dto arrendatarioDto1 = new arrendatario_dto(1L, "Lu Actualizado", "Vargas Gomez", "lu2@javeriana.edu.com", "111111", "qwerty299", false);
        arrendatario_dto arrendatarioDto2 = new arrendatario_dto(5L, "Kevin Arrendatario", "Prueba", "k-jaimes@javeriana.edu.com", "111111", "qwerty322", false);

        when(modelMapper.map(arrendatarios.get(0), arrendatario_dto.class)).thenReturn(arrendatarioDto1);
        when(modelMapper.map(arrendatarios.get(1), arrendatario_dto.class)).thenReturn(arrendatarioDto2);

        // Act
        List<arrendatario_dto> result = arrendatarioService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(arrendatarioDto1, result.get(0));
        assertEquals(arrendatarioDto2, result.get(1));
    }

    @Test
    public void testSaveArrendatario() {
        // Arrange
        arrendatario_dto arrendatarioDto = new arrendatario_dto(1L, "Juan", "Perez", "juan@example.com", "123456789", "contraseñaSegura123", true);
        arrendatario arrendatarioEntity = new arrendatario(1L, "Juan", "Perez", "juan@example.com", "123456789", "contraseñaSegura123", true);

        when(modelMapper.map(arrendatarioDto, arrendatario.class)).thenReturn(arrendatarioEntity);
        when(arrendatarioRepository.save(arrendatarioEntity)).thenReturn(arrendatarioEntity);
        when(modelMapper.map(arrendatarioEntity, arrendatario_dto.class)).thenReturn(arrendatarioDto);

        // Act
        arrendatario_dto result = arrendatarioService.save(arrendatarioDto);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId_arrendatario());
        assertEquals("Juan", result.getNombres());
        assertEquals("Perez", result.getApellidos());
        assertEquals("juan@example.com", result.getCorreo());
        assertEquals("123456789", result.getTelefono());
        assertEquals("contraseñaSegura123", result.getContrasena());
        assertTrue(result.isActivado());
        verify(arrendatarioRepository, times(1)).save(arrendatarioEntity);
    }

    @Test
    public void testUpdateArrendatarioPassword() {
        // Arrange
        Long id = 7L;
        arrendatario_dto arrendatarioDto = new arrendatario_dto(id, "Test Arrendatario", "Test", "testprueba@javeriana.edu.com", "111111", "qwerty3223", false);
        arrendatario existingArrendatario = new arrendatario(id, "Test Arrendatario", "Test", "test@javeriana.edu.com", "111111", "qwerty322", true);

        when(arrendatarioRepository.existsById(id)).thenReturn(true);
        when(modelMapper.map(arrendatarioDto, arrendatario.class)).thenReturn(existingArrendatario);
        when(arrendatarioRepository.save(any(arrendatario.class))).thenReturn(existingArrendatario);
        when(modelMapper.map(existingArrendatario, arrendatario_dto.class)).thenReturn(arrendatarioDto);

        // Act
        arrendatario_dto updatedArrendatario = arrendatarioService.update(arrendatarioDto);

        // Assert
        assertNotNull(updatedArrendatario);
        assertEquals("qwerty3223", updatedArrendatario.getContrasena());
        verify(arrendatarioRepository, times(1)).save(existingArrendatario);
    }

    @Test
    public void testDeleteArrendatario() {
        // Arrange
        Long id = 8L;
        when(arrendatarioRepository.existsById(id)).thenReturn(true);

        // Act
        arrendatarioService.delete(id);

        // Assert
        verify(arrendatarioRepository, times(1)).deleteById(id);
    }
}

