package br.com.sigvet.sigvetapi.common.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonFilter(DiagnosticEntity.DIAGNOSTIC_ENTITY_FILTER_KEY)
@Entity
@Table(name = "diagnostics")
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DiagnosticEntity extends BaseEntity<Long> {

    public static final String DIAGNOSTIC_ENTITY_FILTER_KEY = "diagnosticEntityFilter";

    @Column(length =  255, nullable = false)
    private String diagnosis;

    @Column(columnDefinition = "TEXT")
    private String comments;

    @JoinColumn(nullable = false)
    @ManyToOne
    private ConsultEntity consult;

}
