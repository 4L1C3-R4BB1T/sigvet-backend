package br.com.sigvet.api.infrastructure.entity;

import java.time.LocalDate;

import org.hibernate.annotations.SQLDelete;

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
@Table(name = "animais")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@SQLDelete(sql = "UPDATE animais SET deleted = true WHERE id = ?")
public class AnimalEntity extends BaseEntity {

    @Column(length = 255, nullable = false)
    private String nome;

    @Column(length = 255, nullable = true)
    private String raca;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @JoinColumn(name = "cliente_id", nullable = false)
    @ManyToOne
    private ClienteEntity cliente;
    
}
