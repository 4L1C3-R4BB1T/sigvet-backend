package br.com.sigvet.api.infrastructure.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "vacinacoes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class VacinacaoEntity extends BaseEntity {

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime dataHorario;

    @JoinColumn(name = "veterinario_id", nullable = false)
    @ManyToOne
    private VeterinarioEntity veterinario;

    @JoinColumn(name = "vacina_id", nullable = false)
    @ManyToOne
    private VacinaEntity vacina;

    @JoinColumn(name = "animal_id", nullable = false)
    @ManyToOne
    private AnimalEntity animal;

}
