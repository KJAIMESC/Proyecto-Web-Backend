package com.proyecto1.web;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.proyecto1.web.controllers.ArrendatarioController;
import com.proyecto1.web.dto.arrendatario_dto;
import com.proyecto1.web.services.arrendatario_service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

public class arrendatarioServiceTest {

    @Mock
    private arrendatario_service arrendatarioService;

    @InjectMocks
    private Arrendatario_controller arrendatarioController;

    private MockMvc mockMvc;

    @Test
    public void getArrendatarioByIdTest() throws Exception {
        arrendatario_dto arrendatario = new arrendatario_dto(1, "Lu Actualizado", "Vargas Gomez", "lu2@javeriana.edu.com", "111111", "qwerty299", false);
        when(arrendatarioService.getById(1L)).thenReturn(arrendatario);

        mockMvc = standaloneSetup(arrendatarioController).build();

        mockMvc.perform(get("/arrendatario/{id}", 1))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id_arrendatario").value(1))
               .andExpect(jsonPath("$.nombres").value("Lu Actualizado"))
               .andExpect(jsonPath("$.apellidos").value("Vargas Gomez"))
               .andExpect(jsonPath("$.correo").value("lu2@javeriana.edu.com"))
               .andExpect(jsonPath("$.telefono").value("111111"))
               .andExpect(jsonPath("$.contrasena").value("qwerty299"))
               .andExpect(jsonPath("$.activado").value(false));

        verify(arrendatarioService).getById(1L);
    }
}
