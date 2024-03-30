package com.proyecto1.web.dto;

import com.google.protobuf.Timestamp;
import com.proyecto1.web.entities.EstadoSolicitud;
import com.proyecto1.web.entities.arrendatario;
import com.proyecto1.web.entities.propiedad;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class solicitudes_dto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_solicitud;
    private String nombreSolicitante;
    private Timestamp fechaSolicitud;
    private Timestamp horaSolicitud;
    private Timestamp fechaLlegada;
    private Timestamp fechaSalida;
    private double valor;
    @Column(nullable = true)
    private double calificacion;
    @ManyToOne
    @JoinColumn(name = "id_estadoSolicitud_FK")
    private EstadoSolicitud estadoSolicitud;
    @ManyToOne
    @JoinColumn(name = "id_arrendatario_FK")
    private arrendatario arrendatario;
    @ManyToOne
    @JoinColumn(name = "id_propiedad_FK")
    private propiedad propiedad;
}
