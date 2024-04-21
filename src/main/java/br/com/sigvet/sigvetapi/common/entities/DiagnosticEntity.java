package br.com.sigvet.sigvetapi.common.entities;

import br.com.sigvet.sigvetapi.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "diagnostics")
@SuperBuilder
@Data
@NoArgsConstructor
public class DiagnosticEntity extends BaseEntity<Long> {

    @Column(length =  255, nullable = false)
    private String diagnosis;

    @Column(columnDefinition = "TEXT")
    private String comments;

    @JoinColumn(nullable = false)
    @ManyToOne
    private ConsultEntity consult;
}
