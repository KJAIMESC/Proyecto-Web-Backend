package com.proyecto1.web;

import org.modelmapper.ModelMapper;
import org.modelmapper.Converter;
import org.modelmapper.config.Configuration.AccessLevel;
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
                   .setFieldMatchingEnabled(true)
                   .setFieldAccessLevel(AccessLevel.PRIVATE);

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

        modelMapper.addConverter(tipoIngresoConverter);
        modelMapper.addConverter(arrendatarioConverter);
        modelMapper.addConverter(arrendadorConverter);
        modelMapper.addConverter(estadoSolicitudConverter);

        modelMapper.typeMap(propiedad.class, propiedad_dto.class).addMappings(mapper -> {
            mapper.using(tipoIngresoConverter).map(propiedad::getTipoIngreso, propiedad_dto::setTipoIngreso);
            mapper.using(arrendadorConverter).map(propiedad::getArrendador, propiedad_dto::setArrendador);
        });

        modelMapper.typeMap(solicitudes.class, solicitudes_dto.class).addMappings(mapper -> {
            mapper.using(estadoSolicitudConverter).map(solicitudes::getEstadoSolicitud, solicitudes_dto::setEstadoSolicitud);
            mapper.using(arrendatarioConverter).map(solicitudes::getArrendatario, solicitudes_dto::setArrendatario);
            mapper.map(src -> modelMapper.map(src.getPropiedad(), propiedad_dto.class), solicitudes_dto::setPropiedad);
        });

        return modelMapper;
    }
}
