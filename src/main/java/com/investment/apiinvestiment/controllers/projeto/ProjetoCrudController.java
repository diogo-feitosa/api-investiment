package com.investment.apiinvestiment.controllers.projeto;

import com.investment.apiinvestiment.controllers.CrudController;
import com.investment.apiinvestiment.dtos.ParticipanteDto;
import com.investment.apiinvestiment.dtos.ProjetoDto;
import com.investment.apiinvestiment.forms.ProjetoForm;
import com.investment.apiinvestiment.mappers.ProjetoMapper;
import com.investment.apiinvestiment.models.Projeto;
import com.investment.apiinvestiment.services.filter.FilterMapService;
import com.investment.apiinvestiment.services.projeto.ProjetoCrudService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/projects")
@CrossOrigin(origins = "*")
@Api(value = "/api/v1/projects", tags = "/api/v1/projects")
public class ProjetoCrudController implements CrudController<ProjetoDto, ProjetoForm> {

    @Autowired
    private ProjetoCrudService projetoCrudService;

    @Override
    public ResponseEntity<List<ProjetoDto>> findAll() {
        List<Projeto> projetos = this.projetoCrudService.findAll();
        List<ProjetoDto> projetoDtos = ProjetoMapper.INSTANCE.entityToResponse(projetos);
        return ResponseEntity.ok().body(projetoDtos);
    }

    @Override
    public Page<ProjetoDto> findPageable(LocalDateTime startDate, LocalDateTime endDate, String filters, Pageable pageable) {
        Page<Projeto> projetoPage = this.projetoCrudService.findPageable(startDate, endDate, FilterMapService.filterMap(filters), pageable);
        List<ProjetoDto> projetoDtos = ProjetoMapper.INSTANCE.entityToResponse(projetoPage.getContent());
        return new PageImpl<>(projetoDtos, pageable, projetoPage.getTotalElements());
    }

    @Override
    public ResponseEntity<ProjetoDto> create(ProjetoForm projetoForm) {
        Projeto projeto = this.projetoCrudService.create(projetoForm);
        ProjetoDto projetoDto = ProjetoMapper.INSTANCE.entityToResponse(projeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(projetoDto);
    }

    @Override
    public ResponseEntity<ProjetoDto> findById(Long id) {
        Projeto projeto = this.projetoCrudService.findById(id);
        ProjetoDto projetoDto = ProjetoMapper.INSTANCE.entityToResponse(projeto);
        return ResponseEntity.ok().body(projetoDto);
    }

    @Override
    public ResponseEntity<ProjetoDto> update(Long id, ProjetoForm projetoForm) {
        Projeto projeto = this.projetoCrudService.update(id, projetoForm);
        ProjetoDto projetoDto = ProjetoMapper.INSTANCE.entityToResponse(projeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(projetoDto);
    }

    @Override
    public ResponseEntity<ProjetoDto> delete(Long id) throws IOException {
        Projeto projeto = this.projetoCrudService.delete(id);
        ProjetoDto projetoDto = ProjetoMapper.INSTANCE.entityToResponse(projeto);
        return ResponseEntity.ok().body(projetoDto);
    }
}
