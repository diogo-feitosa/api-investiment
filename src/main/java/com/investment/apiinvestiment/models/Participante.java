package com.investment.apiinvestiment.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "participants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted_at is null")
public class Participante extends EntityModel {

    private static final long serialVersionUID = -5463486063258923926L;

    private String name;

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
        name = "projects_participants",
        joinColumns = @JoinColumn(name = "participant_id"),
        inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Projeto> projetos;
}
