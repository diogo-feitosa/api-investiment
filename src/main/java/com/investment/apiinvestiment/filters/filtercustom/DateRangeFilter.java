package com.investment.apiinvestiment.filters.filtercustom;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class DateRangeFilter<M> {
    public Specification<M> dateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return (root, query, criteriaBuilder) -> {
            if (endDate == null && startDate != null) {
                return criteriaBuilder.greaterThan(root.get("createdAt"), Date.from(startDate.atZone(ZoneId.systemDefault()).toInstant()));
            }

            if (startDate == null && endDate == null) {
                return null;
            }

            return criteriaBuilder.between(root.get("createdAt"), Date.from(startDate.atZone(ZoneId.systemDefault()).toInstant()), Date.from(endDate.atZone(ZoneId.systemDefault()).toInstant()));
        };
    }
}
