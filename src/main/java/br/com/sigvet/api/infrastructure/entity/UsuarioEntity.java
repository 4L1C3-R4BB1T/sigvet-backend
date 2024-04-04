package br.com.sigvet.api.infrastructure.entity;

import br.com.sigvet.api.infrastructure.convert.CPFAttributeConvert;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class UsuarioEntity extends BaseEntity {

    @Column(length = 100, nullable = false)
    protected String usuario;

    @Column(length = 100, nullable = false, unique = true)
    protected String email;

    @Column(length = 100, nullable = false)
    protected String senha;

    @Column(length = 100, nullable = false)
    protected String nome;

    @Convert(converter = CPFAttributeConvert.class)
    @Column(length = 14, nullable = false)
    protected String cpf;

    @Column(length = 18, nullable = true)
    protected String telefone;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private EnderecoEntity endereco;

}
