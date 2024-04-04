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

import com.proyecto1.web.dto.EstadoSolicitud_dto;
import com.proyecto1.web.entities.EstadoSolicitud;
import com.proyecto1.web.repositories.EstadoSolicitud_repository;
import com.proyecto1.web.services.EstadoSolicitud_service;

@SpringBootTest
public class EstadoSolicitudServiceTest {

    @Mock
    private EstadoSolicitud_repository estadoSolicitudRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private EstadoSolicitud_service estadoSolicitudService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetExistingEstadoSolicitud() {
        // Arrange
        Long id = 1L;
        EstadoSolicitud estadoSolicitud = new EstadoSolicitud();
        estadoSolicitud.setId_EstadoSolicitud(id);
        estadoSolicitud.setEstado("Test Estado");

        EstadoSolicitud_dto estadoSolicitudDto = new EstadoSolicitud_dto();
        estadoSolicitudDto.setId_EstadoSolicitud(id);
        estadoSolicitudDto.setEstado("Test Estado");

        when(estadoSolicitudRepository.findById(id)).thenReturn(Optional.of(estadoSolicitud));
        when(modelMapper.map(estadoSolicitud, EstadoSolicitud_dto.class)).thenReturn(estadoSolicitudDto);

        // Act
        EstadoSolicitud_dto result = estadoSolicitudService.get(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId_EstadoSolicitud());
        assertEquals("Test Estado", result.getEstado());
    }

    @Test
    public void testGetNonExistingEstadoSolicitud() {
        // Arrange
        Long id = 1L;
        when(estadoSolicitudRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            estadoSolicitudService.get(id);
        });
        assertEquals("El Estado de Solicitud con ID: 1 no existe", exception.getMessage());
    }

    @Test
    public void testGetAllEstadoSolicitud() {
        // Arrange
        EstadoSolicitud estadoSolicitud1 = new EstadoSolicitud();
        estadoSolicitud1.setId_EstadoSolicitud(1L);
        estadoSolicitud1.setEstado("Test Estado 1");

        EstadoSolicitud estadoSolicitud2 = new EstadoSolicitud();
        estadoSolicitud2.setId_EstadoSolicitud(2L);
        estadoSolicitud2.setEstado("Test Estado 2");

        List<EstadoSolicitud> estadoSolicitudList = Arrays.asList(estadoSolicitud1, estadoSolicitud2);

        EstadoSolicitud_dto estadoSolicitudDto1 = new EstadoSolicitud_dto();
        estadoSolicitudDto1.setId_EstadoSolicitud(1L);
        estadoSolicitudDto1.setEstado("Test Estado 1");

        EstadoSolicitud_dto estadoSolicitudDto2 = new EstadoSolicitud_dto();
        estadoSolicitudDto2.setId_EstadoSolicitud(2L);
        estadoSolicitudDto2.setEstado("Test Estado 2");

        List<EstadoSolicitud_dto> expected = Arrays.asList(estadoSolicitudDto1, estadoSolicitudDto2);

        when(estadoSolicitudRepository.findAll()).thenReturn(estadoSolicitudList);
        when(modelMapper.map(estadoSolicitud1, EstadoSolicitud_dto.class)).thenReturn(estadoSolicitudDto1);
        when(modelMapper.map(estadoSolicitud2, EstadoSolicitud_dto.class)).thenReturn(estadoSolicitudDto2);


        List<EstadoSolicitud_dto> result = estadoSolicitudService.getAll();

     
        assertNotNull(result);
        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    // Similarly, write tests for save, update, and delete methods
}
