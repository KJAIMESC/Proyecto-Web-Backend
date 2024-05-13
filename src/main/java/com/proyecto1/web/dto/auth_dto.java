package com.proyecto1.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class auth_dto {
    private String Token;
    private String Tipo;
    private usuario_dto usuario;
}
