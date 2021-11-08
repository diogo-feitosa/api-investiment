package com.investment.apiinvestiment.services.filter;

import com.investment.apiinvestiment.filters.filtercustom.DateRangeFilter;
import com.investment.apiinvestiment.filters.filtercustom.StatusFilter;
import com.investment.apiinvestiment.filters.filtergeneric.GenericSpecification;
import com.investment.apiinvestiment.filters.filtergeneric.SearchCriteria;
import com.investment.apiinvestiment.filters.filtergeneric.SearchOperation;
import com.investment.apiinvestiment.forms.FilterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FilterDefaultTableService<M> {
    @Autowired
    DateRangeFilter<M> DateRangeFilter;

    @Autowired
    StatusFilter<M> statusFilter;

    public Specification<M> filterDefault(LocalDateTime startDate, LocalDateTime endDate, List<FilterForm> filters) {
        GenericSpecification<M> genericSpesification = new GenericSpecification<M>();
        filters.forEach(filter -> {
            genericSpesification.add(new SearchCriteria(filter.getKey(), filter.getValue(), SearchOperation.MATCH));
        });

        return genericSpesification.or(statusFilter.status(filters)).and(DateRangeFilter.dateRange(startDate, endDate));
    }
}
