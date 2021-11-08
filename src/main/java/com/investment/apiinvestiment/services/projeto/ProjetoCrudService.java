package com.investment.apiinvestiment.services.projeto;

import com.investment.apiinvestiment.exceptions.ObjectNotFoundException;
import com.investment.apiinvestiment.forms.FilterForm;
import com.investment.apiinvestiment.forms.ProjetoForm;
import com.investment.apiinvestiment.models.Projeto;
import com.investment.apiinvestiment.repositories.ProjetoRepository;
import com.investment.apiinvestiment.services.CrudService;
import com.investment.apiinvestiment.services.filter.FilterDefaultTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetoCrudService implements CrudService<Projeto, ProjetoForm> {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private FilterDefaultTableService<Projeto> filterDefaultTableService;


    @Override
    public List<Projeto> findAll() {
        return this.projetoRepository.findAll();
    }

    @Override
    public Page<Projeto> findPageable(LocalDateTime startDate, LocalDateTime endDate, List<FilterForm> filters, Pageable pageable) {
        Specification<Projeto> participanteSpecification =
            this.filterDefaultTableService.filterDefault(startDate, endDate, filters);
        return this.projetoRepository.findAll(participanteSpecification, pageable);
    }

    @Override
    public Projeto findById(Long id) {
        Optional<Projeto> participanteOptional = this.projetoRepository.findById(id);
        return participanteOptional.orElseThrow(() -> new ObjectNotFoundException(
            "Object not found! Id: " + id + ", Type: " + Projeto.class.getName()
        ));
    }

    @Override
    public Projeto create(ProjetoForm projetoForm) {
        Projeto projeto = projetoForm.converterNew();
        return this.projetoRepository.save(projeto);
    }

    @Override
    public Projeto update(Long id, ProjetoForm projetoForm) {
        Optional<Projeto> projetoOptional = projetoRepository.findById(id);

        if (projetoOptional.isEmpty()) {
            throw new ObjectNotFoundException(
                "Object not found! Id: " + id + ", Type: " + Projeto.class.getName()
            );
        }

        Projeto projeto = projetoForm.converterUpdate(
            projetoRepository
        );
        return this.projetoRepository.save(projeto);
    }

    @Override
    public Projeto delete(Long id) {
        Optional<Projeto> projetoOptional = this.projetoRepository.findById(id);
        projetoOptional.get().setDeletedAt(new Date());
        return this.projetoRepository.save(projetoOptional.get());
    }
}
