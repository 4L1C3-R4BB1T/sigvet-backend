package br.com.sigvet.api.infrastructure.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "animais")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnimalEntity extends BaseEntity {
    
    private String nome;

    private String raca;

    private LocalDate dataNascimento;

    @ManyToOne
    private ClienteEntity cliente;
}
