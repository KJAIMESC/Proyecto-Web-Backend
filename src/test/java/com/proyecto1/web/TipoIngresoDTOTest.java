package com.proyecto1.web;

import com.proyecto1.web.dto.tipoIngreso_dto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TipoIngresoDTOTest {
    @Test
    void testConstructorAndGettersSetters() {
        // Arrange
        long idTipoIngreso = 60L;
        String tipo = "Alquiler";

        // Act
        tipoIngreso_dto tipoIngresoDTO = new tipoIngreso_dto();
        tipoIngresoDTO.setId_tipoIngreso(idTipoIngreso);
        tipoIngresoDTO.setTipo(tipo);

        // Assert
        assertNotNull(tipoIngresoDTO);
        assertEquals(idTipoIngreso, tipoIngresoDTO.getId_tipoIngreso());
        assertEquals(tipo, tipoIngresoDTO.getTipo());
    }
}
