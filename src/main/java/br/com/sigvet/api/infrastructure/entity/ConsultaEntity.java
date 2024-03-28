package br.com.sigvet.api.infrastructure.entity;

import java.time.LocalDateTime;

import br.com.sigvet.api.core.enums.ConsultaStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "consultas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ConsultaEntity extends BaseEntity {
    
    private LocalDateTime dataHorario;

    @JoinColumn(name = "animal_id")
    @ManyToOne
    private AnimalEntity animal;

    @JoinColumn(name = "veterinario_id")
    @ManyToOne
    private VeterinarioEntity veterinario;

    @Enumerated(EnumType.STRING)
    private ConsultaStatus status;
}
