package com.investment.apiinvestiment.forms;

import com.investment.apiinvestiment.models.Projeto;
import com.investment.apiinvestiment.models.enums.RiskEnum;
import com.investment.apiinvestiment.repositories.ProjetoRepository;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
@Component
public class ProjetoForm {

    private Long id;

    @Pattern(regexp = "[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ ]+",  message = "Não deve conter números e caracteres especiais.")
    @NotBlank(message = "Este campo não pode estar vazio")
    @Length(min = 3, max = 60, message = "Este campo requer no mínimo 3 caracteres e no máximo 60 caracteres.")
    private String name;

    private Date startDate;

    private Date finalDate;

    private Double value;

    private RiskEnum risk;

    public Projeto converterNew() {
        Projeto projeto = new Projeto();
        projeto.setName(name);
        projeto.setStartDate(startDate);
        projeto.setFinalDate(finalDate);
        projeto.setValue(value);
        projeto.setRisk(risk);
        return projeto;
    }

    public Projeto converterUpdate(
        ProjetoRepository projetoRepository
    ) {
        Projeto projeto = projetoRepository.getById(id);
        if (!projeto.getName().equalsIgnoreCase(name)) {
            projeto.setName(name);
        }
        if (!projeto.getStartDate().equals(startDate)) {
            projeto.setStartDate(startDate);
        }
        if (!projeto.getFinalDate().equals(finalDate)) {
            projeto.setFinalDate(finalDate);
        }
        if (!projeto.getValue().equals(value)) {
            projeto.setValue(value);
        }
        if (!projeto.getRisk().equals(risk)) {
            projeto.setRisk(risk);
        }
        return projeto;
    }
}
