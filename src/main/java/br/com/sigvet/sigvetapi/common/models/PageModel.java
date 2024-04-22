package br.com.sigvet.sigvetapi.common.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Data
@Builder
public class PageModel<T> {

    private Integer currentPage;
    private Integer totalPages;
    private Long totalElements;
    private Integer pageSize;
    private List<T> elements;

    private PageModel(Page<T> page) {
        elements = page.getContent();
        totalPages = page.getTotalPages();
        totalElements = page.getTotalElements();
        pageSize = page.getSize();
        currentPage = page.getNumber();
    }

    public static <T> PageModel<T> createNew(Page<T> page) {
        return new PageModel<>(page);
    }

}
