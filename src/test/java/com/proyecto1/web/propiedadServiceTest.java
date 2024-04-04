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

import com.proyecto1.web.dto.propiedad_dto;
import com.proyecto1.web.entities.propiedad;
import com.proyecto1.web.repositories.propiedad_repository;
import com.proyecto1.web.services.propiedad_service;

public class propiedadServiceTest {

    @Mock
    private propiedad_repository propiedadRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private propiedad_service propiedadService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetExistingPropiedad() {
        // Arrange
        Long id = 1L;
        propiedad propiedadEntity = new propiedad();
        propiedadEntity.setId_propiedad(id);
        propiedadEntity.setDepartamento("Bogota");
        propiedadEntity.setMunicipio("Bogota");
        propiedadEntity.setNombre("Casita");
        propiedadEntity.setDescripcion("miCasita");
        propiedadEntity.setCantitadHabitaciones(10);
        propiedadEntity.setCantidadBanos(2);
        propiedadEntity.setPermitidoMascotas(true);
        propiedadEntity.setPiscina(true);
        propiedadEntity.setValorNoche(100000.0);
        propiedadEntity.setActivado(false);
        // Asumiendo que tipoIngreso y arrendador son configurados apropiadamente

        propiedad_dto propiedadDto = new propiedad_dto();
        propiedadDto.setId_propiedad(id);
        propiedadDto.setDepartamento("Bogota");
        // ... Configura el resto de los campos del DTO de acuerdo a la salida esperada

        when(propiedadRepository.findById(id)).thenReturn(Optional.of(propiedadEntity));
        when(modelMapper.map(propiedadEntity, propiedad_dto.class)).thenReturn(propiedadDto);

        // Act
        propiedad_dto result = propiedadService.get(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId_propiedad());
        assertEquals("Bogota", result.getDepartamento());
        // ... Verificar el resto de los campos
    }

    @Test
    public void testGetNonExistingPropiedad() {
        // Arrange
        Long idInexistente = 3L;
        when(propiedadRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act and Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            propiedadService.get(idInexistente);
        });

        String expectedMessage = "La propiedad con ID: 3 no existe";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testDeleteNonExistingPropiedad() {
        // Arrange
        Long idInexistente = 2L;
        when(propiedadRepository.existsById(idInexistente)).thenReturn(false);

        // Act and Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            propiedadService.delete(idInexistente);
        });

        String expectedMessage = "La propiedad con ID: 2 no existe y por lo tanto no puede ser eliminado";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    

}
