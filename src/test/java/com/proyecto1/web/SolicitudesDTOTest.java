package com.proyecto1.web;

import com.proyecto1.web.dto.solicitudes_dto;
import com.proyecto1.web.dto.EstadoSolicitud_dto;
import com.proyecto1.web.dto.arrendatario_dto;
import com.proyecto1.web.dto.propiedad_dto;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SolicitudesDTOTest {
    @Test
    void testConstructorAndGettersSetters() {
        // Arrange
        long idSolicitud = 50L;
        LocalDateTime fechaSolicitud = LocalDateTime.of(2024, 4, 5, 10, 30);
        LocalDateTime horaSolicitud = LocalDateTime.of(2024, 4, 5, 10, 30);
        LocalDateTime fechaLlegada = LocalDateTime.of(2024, 4, 10, 12, 0);
        LocalDateTime fechaSalida = LocalDateTime.of(2024, 4, 15, 12, 0);
        double valor = 200.0;
        double calificacion = 4.5;
        EstadoSolicitud_dto estadoSolicitud = new EstadoSolicitud_dto();
        arrendatario_dto arrendatario = new arrendatario_dto();
        propiedad_dto propiedad = new propiedad_dto();

        // Act
        solicitudes_dto solicitudDTO = new solicitudes_dto();
        solicitudDTO.setId_solicitud(idSolicitud);
        solicitudDTO.setFechaSolicitud(fechaSolicitud);
        solicitudDTO.setHoraSolicitud(horaSolicitud);
        solicitudDTO.setFechaLlegada(fechaLlegada);
        solicitudDTO.setFechaSalida(fechaSalida);
        solicitudDTO.setValor(valor);
        solicitudDTO.setCalificacion(calificacion);
        solicitudDTO.setEstadoSolicitud(estadoSolicitud);
        solicitudDTO.setArrendatario(arrendatario);
        solicitudDTO.setPropiedad(propiedad);

        // Assert
        assertNotNull(solicitudDTO);
        assertEquals(idSolicitud, solicitudDTO.getId_solicitud());
        assertEquals(fechaSolicitud, solicitudDTO.getFechaSolicitud());
        assertEquals(horaSolicitud, solicitudDTO.getHoraSolicitud());
        assertEquals(fechaLlegada, solicitudDTO.getFechaLlegada());
        assertEquals(fechaSalida, solicitudDTO.getFechaSalida());
        assertEquals(valor, solicitudDTO.getValor());
        assertEquals(calificacion, solicitudDTO.getCalificacion());
        assertEquals(estadoSolicitud, solicitudDTO.getEstadoSolicitud());
        assertEquals(arrendatario, solicitudDTO.getArrendatario());
        assertEquals(propiedad, solicitudDTO.getPropiedad());
    }
}
