package com.investment.apiinvestiment.filters.filtergeneric;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Component
public class GenericSpecification<M> implements Specification<M> {
    private final List<SearchCriteria> list;

    public GenericSpecification() {
        this.list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    /**
     * Apenas o MATCH está sendo utilizado para pesquisa genérica
     *
     * @param root    Mapea uma Entidade
     * @param query   Consulta com querys nativas
     * @param builder Nos permite criar consultas dinâmicas
     * @return Predicate
     */
    @Override
    public Predicate toPredicate(@NotNull Root<M> root, @NotNull CriteriaQuery<?> query, @NotNull CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        if (list.toArray().length == 0) {
            return null;
        }

        for (SearchCriteria criteria : list) {
            if (criteria.getOperation().equals(SearchOperation.MATCH)) {
                if (criteria.getKey().contains(".")) {
                    root.fetch(deepField(criteria, "join"), JoinType.LEFT);
                    predicates.add(
                            builder.like(builder.lower(root.get(deepField(criteria, "join")).get(deepField(criteria, "key")).as(String.class)),
                                    "%" + criteria.getValue().toString().toLowerCase() + "%"));

                } else {
                    predicates.add(builder.like(
                            builder.lower(root.get(criteria.getKey()).as(String.class)),
                            "%" + criteria.getValue().toString().toLowerCase() + "%"));
                }
            }
        }

        return builder.or(predicates.toArray(new Predicate[0]));
    }

    /**
     * Trata as pesquisas em campos profundos(relacionamentos)
     *
     * @param criteria todos os parametros para fazer a busca
     * @param type     tipo de chave
     * @return chave
     */
    private String deepField(SearchCriteria criteria, String type) {
        String join = "";
        String key = "";
        String[] keys = criteria.getKey().split("\\.");

        for (int i = 0; i < keys.length; i++) {
            if (i == 0) {
                join = keys[i];
            } else {
                key = keys[i];
            }
        }

        if (type.equals("join")) {
            return join;
        }

        return key;
    }
}
