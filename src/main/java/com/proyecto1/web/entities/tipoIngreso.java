package com.proyecto1.web.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class tipoIngreso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_tipoIngreso;
    private String tipo;
}
