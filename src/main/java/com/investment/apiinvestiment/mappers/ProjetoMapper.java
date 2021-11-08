package com.investment.apiinvestiment.mappers;

import com.investment.apiinvestiment.dtos.ProjetoDto;
import com.investment.apiinvestiment.models.Projeto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface ProjetoMapper {
    ProjetoMapper INSTANCE = Mappers.getMapper(ProjetoMapper.class);

    List<ProjetoDto> entityToResponse(List<Projeto> projetos);

    @InheritInverseConfiguration
    ProjetoDto entityToResponse(Projeto projetos);
}
