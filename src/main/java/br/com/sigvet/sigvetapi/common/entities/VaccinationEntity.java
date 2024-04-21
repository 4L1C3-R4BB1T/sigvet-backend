package br.com.sigvet.sigvetapi.common.entities;

import br.com.sigvet.sigvetapi.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "vaccinations")
@NoArgsConstructor
@Data
@SuperBuilder
public class VaccinationEntity extends BaseEntity<Long> {

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime dateTime;

    @JoinColumn(nullable = false)
    @ManyToOne
    private VeterinarianEntity veterinarian;

    @JoinColumn(nullable = false)
    @ManyToOne
    private VaccineEntity vaccine;

    @JoinColumn(nullable = false)
    @ManyToOne
    private AnimalEntity animal;

}
