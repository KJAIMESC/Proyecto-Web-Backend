package com.proyecto1.web;

import org.junit.jupiter.api.Test;

import com.proyecto1.web.entities.EstadoSolicitud;
import com.proyecto1.web.entities.arrendatario;
import com.proyecto1.web.entities.propiedad;
import com.proyecto1.web.entities.solicitudes;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class SolicitudesTest {

    @Test
    void testEntityWithConstructors() {
        LocalDateTime now = LocalDateTime.now();
        EstadoSolicitud estadoSolicitud = new EstadoSolicitud();
        arrendatario arrendatarioEntity = new arrendatario();
        propiedad propiedadEntity = new propiedad();

        solicitudes solicitud = new solicitudes(1L, now, now, now, now, 100.0, 5.0, estadoSolicitud, arrendatarioEntity, propiedadEntity);
        
        assertNotNull(solicitud);
        assertEquals(1L, solicitud.getId_solicitud());
        assertEquals(now, solicitud.getFechaSolicitud());
        assertEquals(now, solicitud.getHoraSolicitud());
        assertEquals(now, solicitud.getFechaLlegada());
        assertEquals(now, solicitud.getFechaSalida());
        assertEquals(100.0, solicitud.getValor());
        assertEquals(5.0, solicitud.getCalificacion());
        assertEquals(estadoSolicitud, solicitud.getEstadoSolicitud());
        assertEquals(arrendatarioEntity, solicitud.getArrendatario());
        assertEquals(propiedadEntity, solicitud.getPropiedad());
    }

    @Test
    void testEntityWithSettersAndGetters() {
        LocalDateTime now = LocalDateTime.now();
        EstadoSolicitud estadoSolicitud = new EstadoSolicitud();
        arrendatario arrendatarioEntity = new arrendatario();
        propiedad propiedadEntity = new propiedad();

        solicitudes solicitud = new solicitudes();
        solicitud.setId_solicitud(1L);
        solicitud.setFechaSolicitud(now);
        solicitud.setHoraSolicitud(now);
        solicitud.setFechaLlegada(now);
        solicitud.setFechaSalida(now);
        solicitud.setValor(100.0);
        solicitud.setCalificacion(5.0);
        solicitud.setEstadoSolicitud(estadoSolicitud);
        solicitud.setArrendatario(arrendatarioEntity);
        solicitud.setPropiedad(propiedadEntity);

        assertNotNull(solicitud);
        assertEquals(1L, solicitud.getId_solicitud());
        assertEquals(now, solicitud.getFechaSolicitud());
        assertEquals(now, solicitud.getHoraSolicitud());
        assertEquals(now, solicitud.getFechaLlegada());
        assertEquals(now, solicitud.getFechaSalida());
        assertEquals(100.0, solicitud.getValor());
        assertEquals(5.0, solicitud.getCalificacion());
        assertEquals(estadoSolicitud, solicitud.getEstadoSolicitud());
        assertEquals(arrendatarioEntity, solicitud.getArrendatario());
        assertEquals(propiedadEntity, solicitud.getPropiedad());
    }
}

