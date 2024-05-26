package br.com.sigvet.sigvetapi.common.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.sigvet.sigvetapi.common.entities.enums.ConsultationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonFilter(ConsultEntity.CONSULT_ENTITY_FILTER_KEY)
@Entity
@Table(name = "consults")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@SQLRestriction("deleted is false")
public class ConsultEntity extends BaseEntity<Long> {

    public static final String CONSULT_ENTITY_FILTER_KEY = "consultEntityFilter";

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime dateTime;

    @JoinColumn(nullable = false)
    @ManyToOne
    private AnimalEntity animal;

    @JoinColumn(nullable = false)
    @ManyToOne
    private VeterinarianEntity veterinarian;

    @Enumerated(EnumType.STRING)
    private ConsultationStatus status;
    
}
