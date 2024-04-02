package com.proyecto1.web.entities;

import java.time.LocalDateTime; 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @Column(nullable = true)
    private LocalDateTime fechaSolicitud;
    @Column(nullable = true)
    private LocalDateTime horaSolicitud; 
    @Column(nullable = true)
    private LocalDateTime fechaLlegada;
    @Column(nullable = true)
    private LocalDateTime fechaSalida;
    
    @Column(nullable = false)
    private double valor;
    
    @Column(nullable = true)
    private double calificacion;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estadoSolicitud_FK")
    private EstadoSolicitud estadoSolicitud;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_arrendatario_FK")
    private arrendatario arrendatario;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_propiedad_FK")
    private propiedad propiedad;
}
