package br.com.sigvet.sigvetapi.common.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
public class FilterModel {

    private static final int DEFAULT_PAGE = 0;
    private static final int MAX_SIZE = 100;
    private static final int MIN_SIZE = 1;

    private int size = MAX_SIZE;
    private int page = DEFAULT_PAGE;
    private String sort;
    private String equalFilters;
    private String inFilters;

    public int getPage() {
        return Math.max(DEFAULT_PAGE, page);
    }

    public int getSize() {
        return size < 1 ? MIN_SIZE : size > MAX_SIZE ? MAX_SIZE : size;
    }

    public Pageable toPageable() {
        if (Objects.isNull(sort)) {
            return PageRequest.of(getPage(), getSize());
        }

        List<Sort.Order> orders = Arrays
                .stream(sort.split(","))
                .filter(field -> !field.isEmpty())
                .map(FilterModel::extractOrder)
                .collect(Collectors.toList());
        return PageRequest.of(getPage(), getSize(),  Sort.by(orders));
    }

    private static Sort.Order extractOrder(String field) {
        var startsWithMinus = field.startsWith("-");
        field = field.replaceAll("-", "").trim();
        return startsWithMinus ? new Sort.Order(Sort.Direction.DESC, field) : new Sort.Order(Sort.Direction.ASC, field);
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

        for (var equalFilter : tuples) {
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
