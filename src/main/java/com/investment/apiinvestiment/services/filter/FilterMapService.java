package com.investment.apiinvestiment.services.filter;

import com.investment.apiinvestiment.forms.FilterForm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FilterMapService {
    public static List<FilterForm> filterMap(String filters) {
        List<FilterForm> filterList = new ArrayList<>();
        if (filters == null) {
            return filterList;
        }
        if (filters.contains(";")) {
            for (String filter : filters.split(";")) {
                filterList.addAll(filterMapValues(filter));
            }
        } else {
            filterList.addAll(filterMapValues(filters));
        }
        return filterList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static List<FilterForm> filterMapValues(String filter) {
        List<FilterForm> filters = new ArrayList<>();
        if (!filter.contains(":")) {
            return filters;
        }
        final String[] values = filter.split(":");
        if (values.length > 1) {
            if (values[1].contains(",")) {
                for (String f : values[1].split(",")) {
                    filters.add(new FilterForm(values[0], f));
                }
            } else {
                filters.add(new FilterForm(values[0], values[1]));
            }
        }
        return filters;
    }
}
