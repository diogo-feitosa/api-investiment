package com.investment.apiinvestiment.filters.filtercustom;

import com.investment.apiinvestiment.forms.FilterForm;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatusFilter<M> {
    public static final String ACTIVE_SEARCH = "ACTIVE ATIVO";
    public static final String INACTIVE_SEARCH = "INACTIVE INATIVO";

    public Specification<M> status(List<FilterForm> filters) {
        return (root, query, criteriaBuilder) -> {
            for (FilterForm filter: filters) {
                if (filter.getKey().equals("status")) {
                    boolean statusActive = false;
                    boolean statusInative = false;
                    if (filter.getValue().toString().equalsIgnoreCase("ATIVO") || filter.getValue().toString().equalsIgnoreCase("ACTIVE")) {
                        statusActive = true;
                    } else if (filter.getValue().toString().equalsIgnoreCase("INATIVO") || filter.getValue().toString().equalsIgnoreCase("INACTIVE")) {
                        statusInative = true;
                    } else {
                        if (ACTIVE_SEARCH.toLowerCase().contains(filter.getValue().toString())) {
                            statusActive = true;
                        }
                        if (INACTIVE_SEARCH.toLowerCase().contains(filter.getValue().toString())) {
                            statusInative = true;
                        }
                    }

                    if (statusActive && statusInative) {
                        return criteriaBuilder.or(criteriaBuilder.equal(root.get(filter.getKey()), true), criteriaBuilder.equal(root.get(filter.getKey()), false));
                    } else if (statusActive) {
                        return criteriaBuilder.or(criteriaBuilder.equal(root.get(filter.getKey()), true));
                    } else if (statusInative) {
                        return criteriaBuilder.or(criteriaBuilder.equal(root.get(filter.getKey()), false));
                    }
                }
            }
            return null;
        };
    }
}