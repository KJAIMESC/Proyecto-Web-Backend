package com.proyecto1.web.entities;

import com.google.protobuf.Timestamp;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class solicitudes {
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
