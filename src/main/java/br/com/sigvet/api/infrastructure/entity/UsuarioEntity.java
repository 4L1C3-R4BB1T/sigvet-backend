package br.com.sigvet.api.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
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
@Table(
    name = "usuarios", 
    indexes = {
        @Index(name = "usuarios_index_usuario", columnList = "usuario"),
        @Index(name = "usuarios_index_cpf", columnList = "cpf"),
        @Index(name = "usuarios_index_email", columnList = "email"),
})
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UsuarioEntity extends BaseEntity {

    @Column(length = 100, nullable = false)
    protected String usuario;

    @Column(length = 100, nullable = false, unique = true)
    protected String email;

    @Column(length = 100, nullable = false)
    protected String senha;

    @Column(length = 100, nullable = false)
    protected String nome;

    @Column(length = 14, nullable = false)
    protected String cpf;

    @Column(length = 18, nullable = true)
    protected String telefone;

    @OneToOne(mappedBy = "usuario")
    private EnderecoEntity endereco;

}
