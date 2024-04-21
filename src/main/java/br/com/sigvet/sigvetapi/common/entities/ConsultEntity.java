package br.com.sigvet.sigvetapi.common.entities;

import br.com.sigvet.sigvetapi.common.BaseEntity;
import br.com.sigvet.sigvetapi.common.entities.enums.ConsultationStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "consults")
@Data
@NoArgsConstructor
@SuperBuilder
public class ConsultEntity extends BaseEntity<Long> {

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
