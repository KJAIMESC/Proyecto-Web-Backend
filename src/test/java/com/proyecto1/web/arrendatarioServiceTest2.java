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
public class arrendatarioServiceTest2 {

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
        Long id = 1L;
        arrendatario arrendatarioEntity = new arrendatario();
        arrendatarioEntity.setId_arrendatario(id);
        arrendatario_dto arrendatarioDto = new arrendatario_dto();

        when(arrendatarioRepository.findById(id)).thenReturn(Optional.of(arrendatarioEntity));
        when(modelMapper.map(arrendatarioEntity, arrendatario_dto.class)).thenReturn(arrendatarioDto);

        arrendatario_dto result = arrendatarioService.get(id);

        assertNotNull(result);
    }

    @Test
    public void testGetNonExistingArrendatario() {
        Long idInexistente = 2L;
        when(arrendatarioRepository.findById(idInexistente)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            arrendatarioService.get(idInexistente);
        });

        assertEquals("El arrendatario con ID: 2 no existe", exception.getMessage());
    }

    @Test
    public void testGetAllArrendatarios() {
        List<arrendatario> arrendatarios = Arrays.asList(new arrendatario(), new arrendatario());
        when(arrendatarioRepository.findAll()).thenReturn(arrendatarios);
        List<arrendatario_dto> result = arrendatarioService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testSaveArrendatario() {
        arrendatario_dto arrendatarioDto = new arrendatario_dto();
        arrendatarioDto.setContrasena("ValidPassword");

        arrendatario savedEntity = new arrendatario();
        when(modelMapper.map(arrendatarioDto, arrendatario.class)).thenReturn(savedEntity);
        when(arrendatarioRepository.save(any(arrendatario.class))).thenReturn(savedEntity);
        when(modelMapper.map(savedEntity, arrendatario_dto.class)).thenReturn(arrendatarioDto);

        arrendatario_dto result = arrendatarioService.save(arrendatarioDto);

        assertNotNull(result);
    }

    @Test
    public void testUpdateArrendatario() {
        arrendatario_dto arrendatarioDto = new arrendatario_dto();
        arrendatarioDto.setId_arrendatario(1L);
        arrendatarioDto.setContrasena("ValidPassword");

        when(arrendatarioRepository.existsById(arrendatarioDto.getId_arrendatario())).thenReturn(true);
        arrendatario updatedEntity = new arrendatario();
        when(modelMapper.map(arrendatarioDto, arrendatario.class)).thenReturn(updatedEntity);
        when(arrendatarioRepository.save(any(arrendatario.class))).thenReturn(updatedEntity);
        when(modelMapper.map(updatedEntity, arrendatario_dto.class)).thenReturn(arrendatarioDto);

        arrendatario_dto result = arrendatarioService.update(arrendatarioDto);

        assertNotNull(result);
    }

    @Test
    public void testDeleteArrendatario() {
        Long id = 1L;
        when(arrendatarioRepository.existsById(id)).thenReturn(true);
        doNothing().when(arrendatarioRepository).deleteById(id);

        arrendatarioService.delete(id);

        verify(arrendatarioRepository).deleteById(id);
    }
}
