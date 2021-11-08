package com.investment.apiinvestiment.models;

import com.investment.apiinvestiment.models.enums.RiskEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted_at is null")
public class Projeto extends EntityModel {
    private static final long serialVersionUID = 1026444215942240956L;

    private String name;

    private Date startDate;;

    private Date finalDate;

    private Double value;

    private RiskEnum risk;
}
