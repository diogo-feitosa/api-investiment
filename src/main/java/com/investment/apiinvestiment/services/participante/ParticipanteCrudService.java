package com.investment.apiinvestiment.services.participante;

import com.investment.apiinvestiment.exceptions.ObjectNotFoundException;
import com.investment.apiinvestiment.forms.FilterForm;
import com.investment.apiinvestiment.forms.ParticipanteForm;
import com.investment.apiinvestiment.models.Participante;
import com.investment.apiinvestiment.repositories.ParticipanteRepository;
import com.investment.apiinvestiment.services.CrudService;
import com.investment.apiinvestiment.services.filter.FilterDefaultTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipanteCrudService implements CrudService<Participante, ParticipanteForm> {

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Autowired
    private FilterDefaultTableService<Participante> filterDefaultTableService;

    @Override
    public List<Participante> findAll() {
        return this.participanteRepository.findAll();
    }

    @Override
    public Page<Participante> findPageable(
        LocalDateTime startDate,
        LocalDateTime endDate,
        List<FilterForm> filters,
        Pageable pageable
    ) {
        Specification<Participante> participanteSpecification =
            this.filterDefaultTableService.filterDefault(startDate, endDate, filters);
        return this.participanteRepository.findAll(participanteSpecification, pageable);
    }

    @Override
    public Participante findById(Long id) {
        Optional<Participante> participanteOptional = this.participanteRepository.findById(id);
        return participanteOptional.orElseThrow(() -> new ObjectNotFoundException(
            "Object not found! Id: " + id + ", Type: " + Participante.class.getName()
        ));
    }

    @Override
    public Participante create(ParticipanteForm participanteForm) {
        Participante participante = participanteForm.converterNew();
        return this.participanteRepository.save(participante);
    }

    @Override
    public Participante update(Long id, ParticipanteForm participanteForm) {
        Optional<Participante> participanteOptional = participanteRepository.findById(id);

        if (participanteOptional.isEmpty()) {
            throw new ObjectNotFoundException(
                "Object not found! Id: " + id + ", Type: " + Participante.class.getName()
            );
        }

        Participante participante = participanteForm.converterUpdate(
            participanteRepository
        );
        return this.participanteRepository.save(participante);
    }

    @Override
    public Participante delete(Long id) {
        Optional<Participante> participanteOptional = this.participanteRepository.findById(id);
        participanteOptional.get().setDeletedAt(new Date());
        return this.participanteRepository.save(participanteOptional.get());
    }
}
