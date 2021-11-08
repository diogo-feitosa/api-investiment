package com.investment.apiinvestiment.mappers;

import com.investment.apiinvestiment.dtos.ParticipanteDto;
import com.investment.apiinvestiment.models.Participante;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface ParticipanteMapper {
    ParticipanteMapper INSTANCE = Mappers.getMapper(ParticipanteMapper.class);

    List<ParticipanteDto> entityToResponse(List<Participante> participantes);

    @InheritInverseConfiguration
    ParticipanteDto entityToResponse(Participante participante);
}
