package com.proyecto1.web;

import com.proyecto1.web.dto.arrendatario_dto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ArrendatarioDTOTest {
    @Test
    void testConstructorAndGettersSetters() {
        // Arrange
        long idArrendatario = 20L;
        String nombres = "Elena";
        String apellidos = "Martinez";
        String correo = "elena.martinez@example.com";
        String telefono = "+123456789";
        String contrasena = "mypassword";
        boolean activado = true;

        // Act
        arrendatario_dto arrendatarioDTO = new arrendatario_dto();
        arrendatarioDTO.setId_arrendatario(idArrendatario);
        arrendatarioDTO.setNombres(nombres);
        arrendatarioDTO.setApellidos(apellidos);
        arrendatarioDTO.setCorreo(correo);
        arrendatarioDTO.setTelefono(telefono);
        arrendatarioDTO.setContrasena(contrasena);
        arrendatarioDTO.setActivado(activado);

        // Assert
        assertNotNull(arrendatarioDTO);
        assertEquals(idArrendatario, arrendatarioDTO.getId_arrendatario());
        assertEquals(nombres, arrendatarioDTO.getNombres());
        assertEquals(apellidos, arrendatarioDTO.getApellidos());
        assertEquals(correo, arrendatarioDTO.getCorreo());
        assertEquals(telefono, arrendatarioDTO.getTelefono());
        assertEquals(contrasena, arrendatarioDTO.getContrasena());
        assertEquals(activado, arrendatarioDTO.isActivado());
    }
}
