package com.investment.apiinvestiment.dtos;

import com.investment.apiinvestiment.models.enums.RiskEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProjetoDto {

    private Long id;

    private String name;

    private Date startDate;;

    private Date finalDate;

    private Double value;

    private RiskEnum risk;
}
