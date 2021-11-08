package com.investment.apiinvestiment.repositories;

import com.investment.apiinvestiment.models.Participante;
import com.investment.apiinvestiment.models.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long>, JpaSpecificationExecutor<Participante> {

}

