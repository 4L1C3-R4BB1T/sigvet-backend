package br.com.sigvet.sigvetapi.common.entities;

import java.time.LocalDateTime;
import java.time.LocalTime;

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
@JsonFilter(VaccinationEntity.VACCINATION_ENTITY_FILTER_KEY)
@Entity
@Table(name = "vaccinations")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class VaccinationEntity extends BaseEntity<Long> {

    public static final String VACCINATION_ENTITY_FILTER_KEY = "vaccinationEntityFilter";

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime dateTime;

    @JoinColumn(nullable = false)
    @ManyToOne
    private VeterinarianEntity veterinarian;

    @Column(columnDefinition = "TIME")
    LocalTime hour;

    @JoinColumn(nullable = false)
    @ManyToOne
    private VaccineEntity vaccine;

    @JoinColumn(nullable = false)
    @ManyToOne
    private AnimalEntity animal;

}
