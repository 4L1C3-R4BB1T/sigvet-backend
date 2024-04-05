package br.com.sigvet.api.application.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InFilterModel {
    private String column;
    private List<String> values;
    private boolean isIn;
}