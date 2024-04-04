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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.server.ResponseStatusException;

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

    @Test
    public void testGetNonExistingArrendador() {
        // Arrange
        Long id = 2L;
        when(arrendadorRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            arrendadorService.get(id);
        });

        assertEquals("El Arrendador con ID: 2 no existe", exception.getMessage());
    }

    @Test
    public void testGetAllArrendador() {
        // Arrange
        List<arrendador> arrendadorList = Arrays.asList(
            new arrendador(1L, "Kevin", "Jaimes Carrillo", "k-jaimes@javeriana.edu.co", "00000", "qwerty", false),
            new arrendador(4L, "Lu Actualizado", "Vargas Gomez", "lu@javeriana.edu.com", "111111", "qwerty29", false)
            // ... más instancias de arrendador ...
        );

        List<arrendador_dto> expectedDtoList = Arrays.asList(
            new arrendador_dto(1L, "Kevin", "Jaimes Carrillo", "k-jaimes@javeriana.edu.co", "00000", "qwerty", false),
            new arrendador_dto(4L, "Lu Actualizado", "Vargas Gomez", "lu@javeriana.edu.com", "111111", "qwerty29", false)
            // ... más instancias de arrendador_dto ...
        );

        when(arrendadorRepository.findAll()).thenReturn(arrendadorList);
        for (int i = 0; i < arrendadorList.size(); i++) {
            when(modelMapper.map(arrendadorList.get(i), arrendador_dto.class)).thenReturn(expectedDtoList.get(i));
        }

        // Act
        List<arrendador_dto> result = arrendadorService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(expectedDtoList.size(), result.size());
        for (int i = 0; i < expectedDtoList.size(); i++) {
            assertEquals(expectedDtoList.get(i), result.get(i));
        }
    }

    @Test
    void testCreateArrendador() {
        // Arrange
        Long id = 1L;
        arrendador_dto arrendadorDto = new arrendador_dto();
        arrendadorDto.setId_arrendador(id);
        arrendadorDto.setNombres("Juan");
        arrendadorDto.setApellidos("Perez");
        arrendadorDto.setCorreo("juan@example.com");
        arrendadorDto.setTelefono("123456789");
        arrendadorDto.setContrasena("password");

        arrendador arrendadorEntity = new arrendador();
        arrendadorEntity.setId_arrendador(id);
        arrendadorEntity.setNombres("Juan");
        arrendadorEntity.setApellidos("Perez");
        arrendadorEntity.setCorreo("juan@example.com");
        arrendadorEntity.setTelefono("123456789");
        arrendadorEntity.setContrasena("password");

        when(modelMapper.map(arrendadorDto, arrendador.class)).thenReturn(arrendadorEntity);

        // Act
        arrendadorService.save(arrendadorDto);

        // Assert
        verify(arrendadorRepository).save(arrendadorEntity);
    }

}
