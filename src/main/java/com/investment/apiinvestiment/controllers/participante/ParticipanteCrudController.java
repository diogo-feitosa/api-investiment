package com.investment.apiinvestiment.controllers.participante;

import com.investment.apiinvestiment.controllers.CrudController;
import com.investment.apiinvestiment.dtos.ParticipanteDto;
import com.investment.apiinvestiment.forms.ParticipanteForm;
import com.investment.apiinvestiment.mappers.ParticipanteMapper;
import com.investment.apiinvestiment.models.Participante;
import com.investment.apiinvestiment.services.filter.FilterMapService;
import com.investment.apiinvestiment.services.participante.ParticipanteCrudService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/participants")
@CrossOrigin(origins = "*")
@Api(value = "/api/v1/participants", tags = "/api/v1/participants")
public class ParticipanteCrudController implements CrudController<ParticipanteDto, ParticipanteForm> {

    @Autowired
    private ParticipanteCrudService participanteCrudService;

    @Override
    public ResponseEntity<List<ParticipanteDto>> findAll() {
        List<Participante> participantes = this.participanteCrudService.findAll();
        List<ParticipanteDto> participanteDtos = ParticipanteMapper.INSTANCE.entityToResponse(participantes);
        return ResponseEntity.ok().body(participanteDtos);
    }

    @Override
    public Page<ParticipanteDto> findPageable(LocalDateTime startDate, LocalDateTime endDate, String filters, Pageable pageable) {
        Page<Participante> participantePage = this.participanteCrudService.findPageable(startDate, endDate, FilterMapService.filterMap(filters), pageable);
        List<ParticipanteDto> participanteDtos = ParticipanteMapper.INSTANCE.entityToResponse(participantePage.getContent());
        return new PageImpl<>(participanteDtos, pageable, participantePage.getTotalElements());
    }

    @Override
    public ResponseEntity<ParticipanteDto> create(ParticipanteForm participanteForm) {
        Participante participante = this.participanteCrudService.create(participanteForm);
        ParticipanteDto participanteDto = ParticipanteMapper.INSTANCE.entityToResponse(participante);
        return ResponseEntity.status(HttpStatus.CREATED).body(participanteDto);
    }

    @Override
    public ResponseEntity<ParticipanteDto> findById(Long id) {
        Participante participante = this.participanteCrudService.findById(id);
        ParticipanteDto participanteDto = ParticipanteMapper.INSTANCE.entityToResponse(participante);
        return ResponseEntity.ok().body(participanteDto);
    }

    @Override
    public ResponseEntity<ParticipanteDto> update(Long id, ParticipanteForm participanteForm) {
        Participante participante = this.participanteCrudService.update(id, participanteForm);
        ParticipanteDto participanteDto = ParticipanteMapper.INSTANCE.entityToResponse(participante);
        return ResponseEntity.status(HttpStatus.CREATED).body(participanteDto);
    }

    @Override
    public ResponseEntity<ParticipanteDto> delete(Long id) throws IOException {
        Participante participante = this.participanteCrudService.delete(id);
        ParticipanteDto participanteDto = ParticipanteMapper.INSTANCE.entityToResponse(participante);
        return ResponseEntity.ok().body(participanteDto);
    }
}
