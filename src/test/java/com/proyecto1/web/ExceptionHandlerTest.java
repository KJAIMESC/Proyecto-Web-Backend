
package com.proyecto1.web;

import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.proyecto1.web.exceptions.exceptionHandler;

import jakarta.persistence.EntityNotFoundException;

import java.sql.SQLIntegrityConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionHandlerTest {

    private final exceptionHandler handler = new exceptionHandler();

    @Test
    public void testHandleIllegalArgumentException() {
        IllegalArgumentException ex = new IllegalArgumentException("Invalid Argument");
        ResponseEntity<Object> response = handler.handleIllegalArgumentException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        exceptionHandler.ApiError apiError = (exceptionHandler.ApiError) response.getBody();
        assertEquals("Invalid Argument", apiError.getMessage());
        assertEquals("Error en el Request", apiError.getError());
    }

    @Test
    public void testDataIntegrityViolationExceptionWithDuplicateEntry() {
        SQLIntegrityConstraintViolationException sqlEx = new SQLIntegrityConstraintViolationException("Duplicate entry");
        DataIntegrityViolationException ex = new DataIntegrityViolationException("Test", sqlEx);
        ResponseEntity<Object> response = handler.manejarExcepcionDeIntegridadDeDatos(ex);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        exceptionHandler.ApiError apiError = (exceptionHandler.ApiError) response.getBody();
        assertEquals("Una cuenta con ese correo electrónico ya existe.", apiError.getMessage());
        assertEquals("Correo Duplicado", apiError.getError());
    }

    // Additional test methods for other specific cases of DataIntegrityViolationException...

    @Test
    public void testHandleEntityNotFoundExceptionWithTipoIngreso() {
        EntityNotFoundException ex = new EntityNotFoundException("tipoIngreso");
        ResponseEntity<Object> response = handler.handleEntityNotFound(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        exceptionHandler.ApiError apiError = (exceptionHandler.ApiError) response.getBody();
        assertEquals("El tipo de ingreso asignado no existe o otros datos dependen de él.", apiError.getMessage());
    }

    @Test
    public void testHandleEntityNotFoundExceptionWithArrendador() {
        EntityNotFoundException ex = new EntityNotFoundException("arrendador");
        ResponseEntity<Object> response = handler.handleEntityNotFound(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        exceptionHandler.ApiError apiError = (exceptionHandler.ApiError) response.getBody();
        assertEquals("El arrendador asignado no existeo o otros datos dependen de él.", apiError.getMessage());
    }

    // Additional tests for other scenarios...

    @Test
    public void testDataIntegrityViolationExceptionForPropiedadToTipoIngresoForeignKey() {
        String errorMessage = "`grupo_1_6`.`propiedad`...`id_tipo_ingreso_fk`";
        SQLIntegrityConstraintViolationException sqlEx = new SQLIntegrityConstraintViolationException(errorMessage);
        DataIntegrityViolationException ex = new DataIntegrityViolationException("Test", sqlEx);
        ResponseEntity<Object> response = handler.manejarExcepcionDeIntegridadDeDatos(ex);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        exceptionHandler.ApiError apiError = (exceptionHandler.ApiError) response.getBody();
        assertEquals("El tipo de ingreso asignado no existe.", apiError.getMessage());
    }

    @Test
    public void testDataIntegrityViolationExceptionForPropiedadToArrendadorForeignKey() {
        String errorMessage = "`grupo_1_6`.`propiedad`...`id_arrendador_fk`";
        SQLIntegrityConstraintViolationException sqlEx = new SQLIntegrityConstraintViolationException(errorMessage);
        DataIntegrityViolationException ex = new DataIntegrityViolationException("Test", sqlEx);
        ResponseEntity<Object> response = handler.manejarExcepcionDeIntegridadDeDatos(ex);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        exceptionHandler.ApiError apiError = (exceptionHandler.ApiError) response.getBody();
        assertEquals("El arrendador asignado no existe.", apiError.getMessage());
    }

    @Test
    public void testDataIntegrityViolationExceptionForSolicitudesToEstadoSolicitudForeignKey() {
        String errorMessage = "`grupo_1_6`.`solicitudes`...FKq9lsjulsg3utpah314mvnyfpq";
        SQLIntegrityConstraintViolationException sqlEx = new SQLIntegrityConstraintViolationException(errorMessage);
        DataIntegrityViolationException ex = new DataIntegrityViolationException("Test", sqlEx);
        ResponseEntity<Object> response = handler.manejarExcepcionDeIntegridadDeDatos(ex);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        exceptionHandler.ApiError apiError = (exceptionHandler.ApiError) response.getBody();
        assertEquals("La referencia a Estado Solicitudes en Solicitudes es inválida o no existe.", apiError.getMessage());
    }

    @Test
    public void testDataIntegrityViolationExceptionForSolicitudesToArrendatarioForeignKey() {
        String errorMessage = "`grupo_1_6`.`solicitudes`...FKqca8j1oo9d1qisjrgr8m434rj";
        SQLIntegrityConstraintViolationException sqlEx = new SQLIntegrityConstraintViolationException(errorMessage);
        DataIntegrityViolationException ex = new DataIntegrityViolationException("Test", sqlEx);
        ResponseEntity<Object> response = handler.manejarExcepcionDeIntegridadDeDatos(ex);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        exceptionHandler.ApiError apiError = (exceptionHandler.ApiError) response.getBody();
        assertEquals("La referencia a Arrendatario en Solicitudes es inválida o no existe.", apiError.getMessage());
        assertEquals("Referencia a Arrendatario Inválida", apiError.getError());
    }

    @Test
    public void testDataIntegrityViolationExceptionForSolicitudesToPropiedadForeignKey() {
        String errorMessage = "`grupo_1_6`.`solicitudes`...FK2x8epyvcyta1ots3xbdbwk6q0";
        SQLIntegrityConstraintViolationException sqlEx = new SQLIntegrityConstraintViolationException(errorMessage);
        DataIntegrityViolationException ex = new DataIntegrityViolationException("Test", sqlEx);
        ResponseEntity<Object> response = handler.manejarExcepcionDeIntegridadDeDatos(ex);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        exceptionHandler.ApiError apiError = (exceptionHandler.ApiError) response.getBody();
        assertEquals("La referencia a Propiedad en Solicitudes es inválida o no existe.", apiError.getMessage());
        assertEquals("Referencia a Propiedad Inválida", apiError.getError());
    }
}
