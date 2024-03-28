package br.com.sigvet.api.infrastructure.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "vacinas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class VacinaEntity extends BaseEntity {
    
    private String nome;
    private String fabricante;
    private String lote;
    private Double precoUnitario;
    private Integer estoque;
    private LocalDate dataValidade;
}
