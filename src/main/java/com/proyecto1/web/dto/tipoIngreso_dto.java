package com.proyecto1.web.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class tipoIngreso_dto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tipoIngreso;
    private String tipo;
}
