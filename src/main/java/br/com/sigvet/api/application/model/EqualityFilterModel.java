package br.com.sigvet.api.application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EqualityFilterModel {
    private String column;
    private String value;
    private boolean isEqual;
}
