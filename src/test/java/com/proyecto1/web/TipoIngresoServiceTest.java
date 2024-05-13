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

import com.proyecto1.web.controllers.tipoIngreso_controller;
import com.proyecto1.web.dto.tipoIngreso_dto;
import com.proyecto1.web.entities.tipoIngreso;
import com.proyecto1.web.repositories.tipoIngreso_repository;
import com.proyecto1.web.services.tipoIngreso_service;

public class TipoIngresoServiceTest {

    @Mock
    private tipoIngreso_repository tipoIngresoRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private tipoIngreso_service tipoIngresoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenGetTipoIngresoById_thenReturnTipoIngresoDto() {
        // Arrange
        Long id = 1L;
        tipoIngreso tipoIngresoEntity = new tipoIngreso(id, "rampa");
        tipoIngreso_dto expectedDto = new tipoIngreso_dto(id, "rampa");
        when(tipoIngresoRepository.findById(id)).thenReturn(Optional.of(tipoIngresoEntity));
        when(modelMapper.map(tipoIngresoEntity, tipoIngreso_dto.class)).thenReturn(expectedDto);

        // Act
        tipoIngreso_dto actualDto = tipoIngresoService.get(id);

        // Assert
        assertNotNull(actualDto);
        assertEquals(expectedDto.getId_tipoIngreso(), actualDto.getId_tipoIngreso());
        assertEquals(expectedDto.getTipo(), actualDto.getTipo());
    }

    @Test
public void whenGetAllTipoIngreso_thenReturnListOfTipoIngresoDto() {
    // Arrange
    List<tipoIngreso> tipoIngresoList = Arrays.asList(
        new tipoIngreso(1L, "rampa"),
        new tipoIngreso(2L, "garageActualizado")
    );
    when(tipoIngresoRepository.findAll()).thenReturn(tipoIngresoList);
    when(modelMapper.map(any(tipoIngreso.class), eq(tipoIngreso_dto.class)))
        .thenAnswer(i -> new tipoIngreso_dto(((tipoIngreso) i.getArgument(0)).getId_tipoIngreso(), ((tipoIngreso) i.getArgument(0)).getTipo()));

    // Act
    List<tipoIngreso_dto> result = tipoIngresoService.getAll();

    // Assert
    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals("rampa", result.get(0).getTipo());
    assertEquals("garageActualizado", result.get(1).getTipo());
    }

    @Test
public void whenSaveTipoIngreso_thenReturnTipoIngresoDto() {
    // Arrange
    tipoIngreso_dto newTipoIngresoDto = new tipoIngreso_dto();
    tipoIngreso newTipoIngresoEntity = new tipoIngreso();
    tipoIngreso savedTipoIngresoEntity = new tipoIngreso(7L, "test");

    when(modelMapper.map(newTipoIngresoDto, tipoIngreso.class)).thenReturn(newTipoIngresoEntity);
    when(tipoIngresoRepository.save(newTipoIngresoEntity)).thenReturn(savedTipoIngresoEntity);
    when(modelMapper.map(savedTipoIngresoEntity, tipoIngreso_dto.class)).thenReturn(new tipoIngreso_dto(savedTipoIngresoEntity.getId_tipoIngreso(), savedTipoIngresoEntity.getTipo()));

    // Act
    tipoIngreso_dto result = tipoIngresoService.save(newTipoIngresoDto);

    // Assert
    assertNotNull(result);
    assertEquals(7L, result.getId_tipoIngreso());
    assertEquals("test", result.getTipo());
    }

    @Test
public void whenUpdateTipoIngreso_thenReturnUpdatedTipoIngresoDto() {
    // Arrange
    Long id = 3L;
    tipoIngreso_dto tipoIngresoDto = new tipoIngreso_dto(id, "test");
    tipoIngreso existingTipoIngreso = new tipoIngreso(id, "test");
    tipoIngreso updatedTipoIngreso = new tipoIngreso(id, "test");

    when(tipoIngresoRepository.existsById(id)).thenReturn(true);
    when(modelMapper.map(tipoIngresoDto, tipoIngreso.class)).thenReturn(updatedTipoIngreso);
    when(tipoIngresoRepository.save(updatedTipoIngreso)).thenReturn(updatedTipoIngreso);
    when(modelMapper.map(updatedTipoIngreso, tipoIngreso_dto.class)).thenReturn(tipoIngresoDto);

    // Act
    tipoIngreso_dto result = tipoIngresoService.update(tipoIngresoDto);

    // Assert
    assertNotNull(result);
    assertEquals(id, result.getId_tipoIngreso());
    assertEquals("test", result.getTipo());
    }

@Test
public void whenDeleteTipoIngreso_thenVerifyDeletion() {
    // Arrange
    Long id = 4L;
    when(tipoIngresoRepository.existsById(id)).thenReturn(true);

    // Act
    tipoIngresoService.delete(id);

    // Assert
    verify(tipoIngresoRepository, times(1)).deleteById(id);
}


}
