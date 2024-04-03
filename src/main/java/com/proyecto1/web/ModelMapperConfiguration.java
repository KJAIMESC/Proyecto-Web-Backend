package com.proyecto1.web;

import org.modelmapper.ModelMapper;
import org.modelmapper.Converter;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.proyecto1.web.dto.*;
import com.proyecto1.web.entities.*;

@Configuration
public class ModelMapperConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ModelMapperConfiguration.class);

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                   .setMatchingStrategy(MatchingStrategies.STRICT)
                   .setFieldMatchingEnabled(true);

        Converter<tipoIngreso, tipoIngreso_dto> tipoIngresoConverter = context -> {
            try {
                tipoIngreso source = context.getSource();
                return source == null ? null : modelMapper.map(source, tipoIngreso_dto.class);
            } catch (Exception e) {
                logger.error("Error mapping tipoIngreso to tipoIngreso_dto", e);
                return null;
            }
        };

        Converter<arrendatario, arrendatario_dto> arrendatarioConverter = context -> {
            try {
                arrendatario source = context.getSource();
                return source == null ? null : modelMapper.map(source, arrendatario_dto.class);
            } catch (Exception e) {
                logger.error("Error mapping arrendatario to arrendatario_dto", e);
                return null;
            }
        };

        Converter<arrendador, arrendador_dto> arrendadorConverter = context -> {
            try {
                arrendador source = context.getSource();
                return source == null ? null : modelMapper.map(source, arrendador_dto.class);
            } catch (Exception e) {
                logger.error("Error mapping arrendador to arrendador_dto", e);
                return null;
            }
        };

        Converter<EstadoSolicitud, EstadoSolicitud_dto> estadoSolicitudConverter = context -> {
            try {
                EstadoSolicitud source = context.getSource();
                return source == null ? null : modelMapper.map(source, EstadoSolicitud_dto.class);
            } catch (Exception e) {
                logger.error("Error mapping EstadoSolicitud to EstadoSolicitud_dto", e);
                return null;
            }
        };
        Converter<propiedad, propiedad_dto> propiedadConverter = context -> {
            try {
                propiedad source = context.getSource();
                return source == null ? null : modelMapper.map(source, propiedad_dto.class);
            } catch (Exception e) {
                logger.error("Error mapping propiedad to propiedad_dto", e);
                return null;
            }
        };

        modelMapper.addConverter(tipoIngresoConverter);
        modelMapper.addConverter(arrendatarioConverter);
        modelMapper.addConverter(arrendadorConverter);
        modelMapper.addConverter(estadoSolicitudConverter);
        modelMapper.addConverter(propiedadConverter);

        modelMapper.typeMap(propiedad.class, propiedad_dto.class).addMappings(mapper -> {
            mapper.map(src -> src.getId_propiedad(), propiedad_dto::setId_propiedad);
            mapper.map(src -> src.getImagen(), propiedad_dto::setImagen);
            mapper.map(src -> src.getDepartamento(), propiedad_dto::setDepartamento);
            mapper.map(src -> src.getMunicipio(), propiedad_dto::setMunicipio);
            mapper.map(src -> src.getNombre(), propiedad_dto::setNombre);
            mapper.map(src -> src.getDescripcion(), propiedad_dto::setDescripcion);
            mapper.map(src -> src.getCantitadHabitaciones(), propiedad_dto::setCantitadHabitaciones);
            mapper.map(src -> src.getCantidadBanos(), propiedad_dto::setCantidadBanos);
            mapper.map(src -> src.isPermitidoMascotas(), propiedad_dto::setPermitidoMascotas);
            mapper.map(src -> src.isPiscina(), propiedad_dto::setPiscina);
            mapper.map(src -> src.getValorNoche(), propiedad_dto::setValorNoche);
            mapper.map(src -> src.isActivado(), propiedad_dto::setActivado);
            mapper.using(tipoIngresoConverter).map(propiedad::getTipoIngreso, propiedad_dto::setTipoIngreso);
            mapper.using(arrendadorConverter).map(propiedad::getArrendador, propiedad_dto::setArrendador);
        });

        modelMapper.typeMap(solicitudes.class, solicitudes_dto.class).addMappings(mapper -> {
            mapper.map(src -> src.getId_solicitud(), solicitudes_dto::setId_solicitud);
            mapper.map(src -> src.getFechaSolicitud(), solicitudes_dto::setFechaSolicitud);
            mapper.map(src -> src.getHoraSolicitud(), solicitudes_dto::setHoraSolicitud);
            mapper.map(src -> src.getFechaLlegada(), solicitudes_dto::setFechaLlegada);
            mapper.map(src -> src.getFechaSalida(), solicitudes_dto::setFechaSalida);
            mapper.map(src -> src.getValor(), solicitudes_dto::setValor);
            mapper.map(src -> src.getCalificacion(), solicitudes_dto::setCalificacion);
            mapper.using(estadoSolicitudConverter).map(solicitudes::getEstadoSolicitud, solicitudes_dto::setEstadoSolicitud);
            // mapper.using(arrendatarioConverter).map(solicitudes::getArrendatario, solicitudes_dto::setArrendatario);
            mapper.using(propiedadConverter).map(solicitudes::getPropiedad, solicitudes_dto::setPropiedad);
        });
        
        return modelMapper;
    }
}
