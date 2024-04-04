package com.proyecto1.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.proyecto1.web.controllers.EstadoSolicitud_controller;
import com.proyecto1.web.dto.EstadoSolicitud_dto;

@SpringBootTest
public class EstadoSolicitud_test {

    @Autowired
    EstadoSolicitud_controller estadoSolicitud_controller;

    @Test
    void estadoSolicitudTest() {
        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("-----------------");

        int cantidad = estadoSolicitud_controller.getAll().size();
        
        EstadoSolicitud_dto estadoSolicitud_dto = new EstadoSolicitud_dto(10000,"EstadoSolicitud_test");

        estadoSolicitud_controller.save(estadoSolicitud_dto);
        
        int nuevaCantidad = estadoSolicitud_controller.getAll().size();

        assertEquals(cantidad + 1, nuevaCantidad);

        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("-----------------");

        estadoSolicitud_dto.setEstado("ESTADO SOLICITUD TEST");

        estadoSolicitud_controller.update(estadoSolicitud_dto);
        
        EstadoSolicitud_dto estadoSolicitud_dto2 = estadoSolicitud_controller.get(estadoSolicitud_dto.getId_EstadoSolicitud());

        assertEquals("ESTADO SOLICITUD TEST", estadoSolicitud_dto2.getEstado());

        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("-----------------");
    }
}
