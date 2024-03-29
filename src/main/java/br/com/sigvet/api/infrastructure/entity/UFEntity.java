package br.com.sigvet.api.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(
    name = "ufs",
    indexes = {
        @Index(name = "ufs_index_nome", columnList = "nome")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UFEntity {
    
    @Column(length = 2)
    @Id
    private String sigla;

    @Column(length = 255, nullable = false)
    private String nome;
}
