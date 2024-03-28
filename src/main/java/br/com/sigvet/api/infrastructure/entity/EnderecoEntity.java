package br.com.sigvet.api.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "enderecos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class EnderecoEntity extends BaseEntity {
    
    private String rua;

    private String bairro;

    private String cep;

    private Integer numero;

    @JoinColumn(name = "cidade_id")
    @ManyToOne
    private CidadeEntity cidade;

    @JoinColumn(name = "usuario_id")
    @OneToOne
    private UsuarioEntity usuario;
}
