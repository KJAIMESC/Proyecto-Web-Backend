package com.proyecto1.web;

import com.proyecto1.web.dto.arrendador_dto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ArrendadorDTOTest {
    @Test
    void testConstructorAndGettersSetters() {
        // Arrange
        long idArrendador = 10L;
        String nombres = "Alice";
        String apellidos = "Smith";
        String correo = "alice.smith@example.com";
        String telefono = "+987654321";
        String contrasena = "securepassword";
        boolean activado = true;

        // Act
        arrendador_dto arrendadorDTO = new arrendador_dto();
        arrendadorDTO.setId_arrendador(idArrendador);
        arrendadorDTO.setNombres(nombres);
        arrendadorDTO.setApellidos(apellidos);
        arrendadorDTO.setCorreo(correo);
        arrendadorDTO.setTelefono(telefono);
        arrendadorDTO.setContrasena(contrasena);
        arrendadorDTO.setActivado(activado);

        // Assert
        assertNotNull(arrendadorDTO);
        assertEquals(idArrendador, arrendadorDTO.getId_arrendador());
        assertEquals(nombres, arrendadorDTO.getNombres());
        assertEquals(apellidos, arrendadorDTO.getApellidos());
        assertEquals(correo, arrendadorDTO.getCorreo());
        assertEquals(telefono, arrendadorDTO.getTelefono());
        assertEquals(contrasena, arrendadorDTO.getContrasena());
        assertEquals(activado, arrendadorDTO.isActivado());
    }
}
