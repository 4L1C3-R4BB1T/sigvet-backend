package br.com.sigvet.api.application.model;

import static br.com.sigvet.api.application.constant.FilterConstants.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import lombok.Setter;

@Setter
public class FilterModel {
    
    private Integer limit;
    private Integer page;
    private String sort;
    private String equalFilters;
    private String inFilters;

    public FilterModel(Map<String, String> params) {
        limit = params.containsKey(LIMIT_KEY) ? Integer.valueOf(params.get(LIMIT_KEY)) : DEFAULT_LIMIT;
        page = params.containsKey(PAGE_KEY) ? Integer.valueOf(params.get(PAGE_KEY)) : DEFAULT_PAGE;
        sort = params.containsKey(SORT_KEY) ? params.get(SORT_KEY) : DEFAULT_SORT;
        equalFilters = params.containsKey(EQUAL_FILTERS_KEY) ? params.get(EQUAL_FILTERS_KEY) : DEFAULT_EQUAL_FILTERS;
        inFilters = params.containsKey(IN_FILTERS_KEY) ? params.get(IN_FILTERS_KEY) : DEFAULT_IN_FILTERS;
    }

    public int getPage() {
        return Math.max(page, DEFAULT_PAGE);
    }

    public int getLimit() {
        if (limit > MAX_LIMIT) {
            return MAX_LIMIT; 
        } 
        else if (limit < 1) {
            return 1;
        } 
        else {
            return limit;
        }
    }

    public Pageable toPageable() {
        List<Order> orders = Arrays
                .stream(sort.split(","))
                .filter(field -> !field.isEmpty())
                .map(FilterModel::extractOrder)
                .collect(Collectors.toList());
        return PageRequest.of(getPage(), getLimit(),  Sort.by(orders));
    }
    
    private static Order extractOrder(String field) {
        var startsWithMinus = field.startsWith("-");
        field = field.replaceAll("-", "").trim();
        return startsWithMinus ? new Order(Direction.DESC, field) : new Order(Direction.ASC, field);
    }

    public List<InFilterModel> getInFilters() {
        List<InFilterModel> filters = new ArrayList<>();

        if (Objects.isNull(inFilters) || inFilters.trim().isEmpty()) 
            return filters;

        var tuples = inFilters.split(";");

        for (var tuple: tuples) {
            var columnAndInValues = tuple.split(":");

            if (columnAndInValues.length != 2)
                continue;
            
            var column = columnAndInValues[0];
            var isIn = !column.startsWith("~");
            var values = Arrays.asList(columnAndInValues[1].split(","));
            var inFilterModel = new InFilterModel(column.replace("~", ""), values, isIn);
            filters.add(inFilterModel);
        }

        return filters;
    }

    public List<EqualityFilterModel> getEqualFilters() {
        List<EqualityFilterModel> filters = new ArrayList<>();

        if (Objects.isNull(equalFilters) || equalFilters.trim().isEmpty())   
            return filters;

        var tuples = equalFilters.split(";");

        for (var equalFilter: tuples) {
            var columnCriteria = equalFilter.split(":");

            if (columnCriteria.length != 2)
                continue;
            
            var column = columnCriteria[0];
            var criteria = columnCriteria[1];

            if (criteria.startsWith("=") || criteria.startsWith("!=")) {
                var filter = new EqualityFilterModel(column, criteria.replaceAll("=|!=", ""), criteria.startsWith("="));
                filters.add(filter);
            }
        }

        return filters;
    }

}
