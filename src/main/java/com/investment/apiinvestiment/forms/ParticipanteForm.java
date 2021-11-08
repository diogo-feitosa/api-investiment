package com.investment.apiinvestiment.forms;

import com.investment.apiinvestiment.models.Participante;
import com.investment.apiinvestiment.repositories.ParticipanteRepository;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Component
public class ParticipanteForm {

    private Long id;

    @Pattern(regexp = "[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ ]+",  message = "Não deve conter números e caracteres especiais.")
    @NotBlank(message = "Este campo não pode estar vazio")
    @Length(min = 3, max = 60, message = "Este campo requer no mínimo 3 caracteres e no máximo 60 caracteres.")
    private String name;


    public Participante converterNew() {
        Participante participante = new Participante();
        participante.setName(name);
        return participante;
    }

    public Participante converterUpdate(
        ParticipanteRepository participanteRepository
    ) {
        Participante participante = participanteRepository.getById(id);
        if (!participante.getName().equals(name)) {
            participante.setName(name);
        }
        return participante;
    }
}
