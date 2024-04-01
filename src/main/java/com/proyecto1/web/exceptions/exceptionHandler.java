package com.proyecto1.web.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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

    //DUPLICATE UNIQUE EMAIL EXCEPTION
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> manejarExcepcionDeIntegridadDeDatos(DataIntegrityViolationException ex) {
        Throwable rootCause = ex.getMostSpecificCause();
        if (rootCause instanceof SQLIntegrityConstraintViolationException) {
            SQLIntegrityConstraintViolationException sqlEx = (SQLIntegrityConstraintViolationException) rootCause;
            if (sqlEx.getMessage().contains("Duplicate entry")) {
                String mensajeDetallado = "Una cuenta con ese correo electrónico ya existe.";
                //ERROR HANDLING FOR ARRENDADOR
                if (sqlEx.getMessage().contains("for key 'arrendador")) {
                    mensajeDetallado = "Una cuenta de arrendador con ese correo electrónico ya existe.";
                }
                //ERROR HANDLING FOR ARRENDATARIO
                else if (sqlEx.getMessage().contains("for key 'arrendatario")) {
                    mensajeDetallado = "Una cuenta de arrendatario con ese correo electrónico ya existe.";
                }
                ApiError apiError = new ApiError(HttpStatus.CONFLICT, mensajeDetallado, "Correo Duplicado");
                return new ResponseEntity<>(apiError, apiError.getStatus());
            }
        }
        //GENERAL INTEGRITY VIOLATION ERROR
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Error de integridad de datos.", "Violación de Integridad de Datos");
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
