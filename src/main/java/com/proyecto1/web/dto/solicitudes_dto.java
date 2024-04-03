package com.proyecto1.web.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class solicitudes_dto {
    private long id_solicitud;
    private LocalDateTime fechaSolicitud;
    private LocalDateTime horaSolicitud;
    private LocalDateTime fechaLlegada;
    private LocalDateTime fechaSalida;
    private double valor;
    private double calificacion;
    private EstadoSolicitud_dto estadoSolicitud;
    // private arrendatario_dto arrendatario;
    private propiedad_dto propiedad;
 }
