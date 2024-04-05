package br.com.sigvet.api.infrastructure.entity;

import org.hibernate.annotations.SQLDelete;

import br.com.sigvet.api.infrastructure.convert.CEPAttributeConvert;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
@SQLDelete(sql = "UPDATE enderecos SET deleted = true WHERE id = ?")
public class EnderecoEntity extends BaseEntity {

    @Column(length = 255, nullable = false)
    private String rua;

    @Column(length = 255, nullable = false)
    private String bairro;

    @Convert(converter = CEPAttributeConvert.class)
    @Column(length = 8, nullable = false)
    private String cep;

    @Column(nullable = false)
    private Integer numero;

    @JoinColumn(name = "cidade_id", nullable = false)
    @ManyToOne
    private CidadeEntity cidade;

    @JoinColumn(name = "usuario_id", nullable = false)
    @OneToOne
    private UsuarioEntity usuario;

}
