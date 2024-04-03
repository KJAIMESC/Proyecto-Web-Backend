package com.proyecto1.web;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.proyecto1.web.dto.*;
import com.proyecto1.web.entities.*;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                   .setMatchingStrategy(MatchingStrategies.STRICT)
                   .setFieldMatchingEnabled(true);

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
            mapper.map(propiedad::getTipoIngreso, propiedad_dto::setTipoIngreso);
            mapper.map(propiedad::getArrendador, propiedad_dto::setArrendador);
        });

        modelMapper.typeMap(solicitudes.class, solicitudes_dto.class).addMappings(mapper -> {
            mapper.map(solicitudes::getId_solicitud, solicitudes_dto::setId_solicitud);
            mapper.map(solicitudes::getFechaSolicitud, solicitudes_dto::setFechaSolicitud);
            mapper.map(solicitudes::getHoraSolicitud, solicitudes_dto::setHoraSolicitud);
            mapper.map(solicitudes::getFechaLlegada, solicitudes_dto::setFechaLlegada);
            mapper.map(solicitudes::getFechaSalida, solicitudes_dto::setFechaSalida);
            mapper.map(solicitudes::getValor, solicitudes_dto::setValor);
            mapper.map(solicitudes::getCalificacion, solicitudes_dto::setCalificacion);
            mapper.map(solicitudes::getEstadoSolicitud, solicitudes_dto::setEstadoSolicitud);
            mapper.map(solicitudes::getPropiedad, solicitudes_dto::setPropiedad);
        });
        
        return modelMapper;
    }
}
