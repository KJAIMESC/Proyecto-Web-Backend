package com.proyecto1.web;

import org.junit.jupiter.api.Test;

import com.proyecto1.web.entities.arrendador;

import static org.junit.jupiter.api.Assertions.*;

class ArrendadorTest {

    @Test
    void testEntityWithConstructors() {
        String nombres = "John";
        String apellidos = "Doe";
        String correo = "johndoe@example.com";
        String telefono = "5551234";
        String contrasena = "myPassword";
        boolean activado = true;

        arrendador arrendadorEntity = new arrendador(1L, nombres, apellidos, correo, telefono, contrasena, activado);
        
        assertNotNull(arrendadorEntity);
        assertEquals(1L, arrendadorEntity.getId_arrendador());
        assertEquals(nombres, arrendadorEntity.getNombres());
        assertEquals(apellidos, arrendadorEntity.getApellidos());
        assertEquals(correo, arrendadorEntity.getCorreo());
        assertEquals(telefono, arrendadorEntity.getTelefono());
        assertEquals(contrasena, arrendadorEntity.getContrasena());
        assertEquals(activado, arrendadorEntity.isActivado());
    }

    @Test
    void testEntityWithSettersAndGetters() {
        String nombres = "Alice";
        String apellidos = "Smith";
        String correo = "alicesmith@example.com";
        String telefono = "5554321";
        String contrasena = "mySecurePassword";
        boolean activado = false;

        arrendador arrendadorEntity = new arrendador();
        arrendadorEntity.setId_arrendador(2L);
        arrendadorEntity.setNombres(nombres);
        arrendadorEntity.setApellidos(apellidos);
        arrendadorEntity.setCorreo(correo);
        arrendadorEntity.setTelefono(telefono);
        arrendadorEntity.setContrasena(contrasena);
        arrendadorEntity.setActivado(activado);

        assertNotNull(arrendadorEntity);
        assertEquals(2L, arrendadorEntity.getId_arrendador());
        assertEquals(nombres, arrendadorEntity.getNombres());
        assertEquals(apellidos, arrendadorEntity.getApellidos());
        assertEquals(correo, arrendadorEntity.getCorreo());
        assertEquals(telefono, arrendadorEntity.getTelefono());
        assertEquals(contrasena, arrendadorEntity.getContrasena());
        assertEquals(activado, arrendadorEntity.isActivado());
    }
}
