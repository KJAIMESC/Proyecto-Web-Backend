package com.proyecto1.web;

import org.junit.jupiter.api.Test;

import com.proyecto1.web.entities.arrendatario;

import static org.junit.jupiter.api.Assertions.*;

class ArrendatarioTest {

    @Test
    void testEntityWithConstructors() {
        String nombres = "Juan";
        String apellidos = "Pérez";
        String correo = "juan.perez@example.com";
        String telefono = "123456789";
        String contrasena = "password123";
        boolean activado = true;

        arrendatario arrendatarioEntity = new arrendatario(1L, nombres, apellidos, correo, telefono, contrasena, activado);
        
        assertNotNull(arrendatarioEntity);
        assertEquals(1L, arrendatarioEntity.getId_arrendatario());
        assertEquals(nombres, arrendatarioEntity.getNombres());
        assertEquals(apellidos, arrendatarioEntity.getApellidos());
        assertEquals(correo, arrendatarioEntity.getCorreo());
        assertEquals(telefono, arrendatarioEntity.getTelefono());
        assertEquals(contrasena, arrendatarioEntity.getContrasena());
        assertEquals(activado, arrendatarioEntity.isActivado());
    }

    @Test
    void testEntityWithSettersAndGetters() {
        String nombres = "Ana";
        String apellidos = "Gomez";
        String correo = "ana.gomez@example.com";
        String telefono = "987654321";
        String contrasena = "securePassword";
        boolean activado = false;

        arrendatario arrendatarioEntity = new arrendatario();
        arrendatarioEntity.setId_arrendatario(2L);
        arrendatarioEntity.setNombres(nombres);
        arrendatarioEntity.setApellidos(apellidos);
        arrendatarioEntity.setCorreo(correo);
        arrendatarioEntity.setTelefono(telefono);
        arrendatarioEntity.setContrasena(contrasena);
        arrendatarioEntity.setActivado(activado);

        assertNotNull(arrendatarioEntity);
        assertEquals(2L, arrendatarioEntity.getId_arrendatario());
        assertEquals(nombres, arrendatarioEntity.getNombres());
        assertEquals(apellidos, arrendatarioEntity.getApellidos());
        assertEquals(correo, arrendatarioEntity.getCorreo());
        assertEquals(telefono, arrendatarioEntity.getTelefono());
        assertEquals(contrasena, arrendatarioEntity.getContrasena());
        assertEquals(activado, arrendatarioEntity.isActivado());
    }
}
