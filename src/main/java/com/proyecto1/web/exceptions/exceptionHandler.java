package com.proyecto1.web.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ControllerAdvice
public class exceptionHandler extends ResponseEntityExceptionHandler{

    //GENERAL INVALID REQUEST EXCEPTION
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), "Error en el Request");
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    //DATA INTEGRITY VIOLATIONS
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> manejarExcepcionDeIntegridadDeDatos(DataIntegrityViolationException ex) {
        Throwable rootCause = ex.getMostSpecificCause();
        ApiError apiError;
        if (rootCause instanceof SQLIntegrityConstraintViolationException) {
            SQLIntegrityConstraintViolationException sqlEx = (SQLIntegrityConstraintViolationException) rootCause;
            String mensajeDetallado = "Error de integridad de datos.";
            String errorType = "Violación de Integridad de Datos";

            //ERROR HANDLING FOR DUPLICATE EMAIL
            if (sqlEx.getMessage().contains("Duplicate entry")) {
                mensajeDetallado = "Una cuenta con ese correo electrónico ya existe.";
                errorType = "Correo Duplicado";
            }
            //ERROR HANDLING FOR FOREIGN KEY (PROPIEDAD TO TIPO_INGRESO)
            else if (sqlEx.getMessage().contains("`grupo_1_6`.`propiedad`") && sqlEx.getMessage().contains("`id_tipo_ingreso_fk`")) {
                mensajeDetallado = "El tipo de ingreso asignado no existe.";
            }
            //ERROR HANDLING FOR FOREIGN KEY (PROPIEDAD TO ARRENDADOR)
            else if (sqlEx.getMessage().contains("`grupo_1_6`.`propiedad`") && sqlEx.getMessage().contains("`id_arrendador_fk`")) {
                mensajeDetallado = "El arrendador asignado no existe.";
            }
            //ERROR HANDLING FOR FOREIGN KEY (SOLICITUDES TO ESTADOSOLICITUD)
            else if (sqlEx.getMessage().contains("`grupo_1_6`.`solicitudes`") && sqlEx.getMessage().contains("FKq9lsjulsg3utpah314mvnyfpq")) {
                mensajeDetallado = "La referencia a Estado Solicitudes en Solicitudes es inválida o no existe.";
            }
            //ERROR HANDLING FOR FOREIGN KEY (SOLICITUDES TO ARRENDATARIO)
            else if (sqlEx.getMessage().contains("`grupo_1_6`.`solicitudes`") && sqlEx.getMessage().contains("FKqca8j1oo9d1qisjrgr8m434rj")) {
                mensajeDetallado = "La referencia a Arrendatario en Solicitudes es inválida o no existe.";
                errorType = "Referencia a Arrendatario Inválida";
            }
            //ERROR HANDLING FOR FOREIGN KEY (SOLICITUDES TO PROPIEDAD)
            else if (sqlEx.getMessage().contains("`grupo_1_6`.`solicitudes`") && sqlEx.getMessage().contains("FK2x8epyvcyta1ots3xbdbwk6q0")) {
                mensajeDetallado = "La referencia a Propiedad en Solicitudes es inválida o no existe.";
                errorType = "Referencia a Propiedad Inválida";
            }

            apiError = new ApiError(HttpStatus.CONFLICT, mensajeDetallado, errorType);
        } else {
            //GENERAL INTEGRITY VIOLATION ERROR
            apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Error de integridad de datos.", "Violación de Integridad de Datos");
        }

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    //ENTITY NOT FOUND EXCEPTION
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        String detailedMessage = ex.getMessage();
        
        if (detailedMessage.contains("tipoIngreso")) {
            detailedMessage = "El tipo de ingreso asignado no existe o otros datos dependen de él.";
        } else if (detailedMessage.contains("arrendador")) {
            detailedMessage = "El arrendador asignado no existeo o otros datos dependen de él.";
        }
        
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, detailedMessage, "Entity Not Found");
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApiError {

        private HttpStatus status;
        private String message;
        private String error;
    }

}
