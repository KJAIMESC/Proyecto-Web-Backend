package com.proyecto1.web.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto1.web.dto.solicitudes_dto;
import com.proyecto1.web.entities.EstadoSolicitud;
import com.proyecto1.web.entities.arrendatario;
import com.proyecto1.web.entities.propiedad;
import com.proyecto1.web.entities.solicitudes;
import com.proyecto1.web.repositories.EstadoSolicitud_repository;
import com.proyecto1.web.repositories.propiedad_repository;
import com.proyecto1.web.repositories.solicitudes_repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class solicitudes_service {

    private final solicitudes_repository solicitudes_repository;
    private final EstadoSolicitud_repository estadoSolicitud_repository;
    private final propiedad_repository propiedad_repository;
    private final ModelMapper modelMapper;

    private final String message = "La solicitud con ID: ";
    private final String noExiste = " no existe";
    private final String noPuedeSEEEERRR = " no existe y por lo tanto no puede ser eliminada";

    @Autowired
    public solicitudes_service(solicitudes_repository solicitudes_repository, EstadoSolicitud_repository estadoSolicitud_repository, propiedad_repository propiedad_repository, ModelMapper modelMapper) {
        this.solicitudes_repository = solicitudes_repository;
        this.estadoSolicitud_repository = estadoSolicitud_repository;
        this.propiedad_repository = propiedad_repository;
        this.modelMapper = modelMapper;
    }

    // GET BY ID
    @Transactional
    public solicitudes_dto get(Long id) {
        Optional<solicitudes> solicitudes_optional = solicitudes_repository.findById(id);
        if (!solicitudes_optional.isPresent()) {
            throw new IllegalArgumentException(message + id + noExiste);
        }
        return modelMapper.map(solicitudes_optional.get(), solicitudes_dto.class);
    }

    // RETURN SOLICITUDES LIST
    @Transactional
    public List<solicitudes_dto> getAll() {
        List<solicitudes> solicitudesList = (List<solicitudes>) solicitudes_repository.findAll();
        return solicitudesList.stream().map(solicitudes -> modelMapper.map(solicitudes, solicitudes_dto.class)).collect(Collectors.toList());
    }

    // SAVE SOLICITUDES
    @Transactional
    public solicitudes_dto save(solicitudes_dto solicitudes_dto) {
        solicitudes solicitudes = modelMapper.map(solicitudes_dto, solicitudes.class);

        // Ensure that EstadoSolicitud is managed
        final EstadoSolicitud estadoSolicitud = solicitudes.getEstadoSolicitud();
        if (estadoSolicitud != null) {
            final EstadoSolicitud managedEstadoSolicitud;
            if (estadoSolicitud.getId_EstadoSolicitud() == 0) {
                managedEstadoSolicitud = estadoSolicitud_repository.save(estadoSolicitud);
            } else {
                managedEstadoSolicitud = estadoSolicitud_repository.findById(estadoSolicitud.getId_EstadoSolicitud())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid EstadoSolicitud ID: " + estadoSolicitud.getId_EstadoSolicitud()));
            }
            solicitudes.setEstadoSolicitud(managedEstadoSolicitud);
        } else {
            EstadoSolicitud defaultEstadoSolicitud = estadoSolicitud_repository.findById(1L)
                    .orElseThrow(() -> new IllegalArgumentException("Default EstadoSolicitud ID: 1 not found"));
            solicitudes.setEstadoSolicitud(defaultEstadoSolicitud);
        }

        solicitudes = solicitudes_repository.save(solicitudes);
        return modelMapper.map(solicitudes, solicitudes_dto.class);
    }

    // DELETE SOLICITUDES BY ID
    @Transactional
    public void delete(Long id) {
        if (!solicitudes_repository.existsById(id)) {
            throw new IllegalArgumentException(message + id + noPuedeSEEEERRR);
        }
        solicitudes_repository.deleteById(id);
    }

    @Transactional
    public List<solicitudes_dto> getAllSolicitudesForAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String) {
            String jsonString = (String) principal;
            Long authenticatedUserId = null;
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(jsonString);
                authenticatedUserId = jsonNode.get("id").asLong();
            } catch (Exception e) {
                throw new RuntimeException("Failed to parse JSON string: " + jsonString, e);
            }

            List<solicitudes> solicitudesList = solicitudes_repository.findAllByAuthenticatedUserId(authenticatedUserId);
            return solicitudesList.stream()
                    .map(solicitudes -> modelMapper.map(solicitudes, solicitudes_dto.class))
                    .collect(Collectors.toList());
        } else {
            throw new IllegalStateException("Unexpected principal type: " + principal.getClass().getName());
        }
    }

    @Transactional
    public List<solicitudes_dto> getAllSolicitudesByPropiedadId(Long idPropiedad) {
        List<solicitudes> solicitudesList = solicitudes_repository.findAllByPropiedadIdPropiedad(idPropiedad);
        return solicitudesList.stream()
                .map(solicitud -> modelMapper.map(solicitud, solicitudes_dto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public solicitudes_dto saveForAuthenticatedUser(solicitudes_dto solicitudesDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String) {
            String jsonString = (String) principal;
            Long authenticatedUserId;
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(jsonString);
                authenticatedUserId = jsonNode.get("id").asLong();
            } catch (Exception e) {
                throw new RuntimeException("Failed to parse JSON string: " + jsonString, e);
            }

            solicitudes solicitudes = modelMapper.map(solicitudesDto, solicitudes.class);
            arrendatario arrendatario = new arrendatario();
            arrendatario.setId_arrendatario(authenticatedUserId);
            solicitudes.setArrendatario(arrendatario);

            EstadoSolicitud defaultEstadoSolicitud = estadoSolicitud_repository.findById(1L)
                    .orElseThrow(() -> new IllegalArgumentException("Default EstadoSolicitud ID: 1 not found"));
            solicitudes.setEstadoSolicitud(defaultEstadoSolicitud);

            // Set the current date and time for fechaSolicitud and horaSolicitud
            LocalDateTime now = LocalDateTime.now();
            solicitudes.setFechaSolicitud(now);
            solicitudes.setHoraSolicitud(now);

            // Default calificacion to 0
            solicitudes.setCalificacion(0.0);

            // Fetch the propiedad to get valorNoche
            final Long propiedadId = solicitudes.getPropiedad().getId_propiedad();
            propiedad propiedad = propiedad_repository.findById(propiedadId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Propiedad ID: " + propiedadId));

            // Calculate the valor
            long daysBetween = ChronoUnit.DAYS.between(solicitudes.getFechaLlegada(), solicitudes.getFechaSalida());
            double valorNoche = propiedad.getValorNoche();

            // Print the daysBetween and valorNoche values
            System.out.println("Days between: " + daysBetween);
            System.out.println("Valor por noche: " + valorNoche);

            solicitudes.setValor(daysBetween * valorNoche);

            solicitudes = solicitudes_repository.save(solicitudes);
            return modelMapper.map(solicitudes, solicitudes_dto.class);
        } else {
            throw new IllegalStateException("Unexpected principal type: " + principal.getClass().getName());
        }
    }
}
