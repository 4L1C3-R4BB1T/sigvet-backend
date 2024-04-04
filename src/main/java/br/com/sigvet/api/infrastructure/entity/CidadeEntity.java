package br.com.sigvet.api.infrastructure.entity;

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
@Table(name = "cidades")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class CidadeEntity extends BaseEntity {
    
    @Column(length = 255, nullable = false)
    private String nome;

    @JoinColumn(name = "uf_sigla", nullable = false, referencedColumnName = "sigla", columnDefinition = "CHAR(2)")
    @ManyToOne
    private UFEntity uf;    
}
