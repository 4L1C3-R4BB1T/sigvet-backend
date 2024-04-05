package br.com.sigvet.api.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "veterinarios")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "id")
public class VeterinarioEntity extends UsuarioEntity {

    @Column(length = 255, nullable = false)
    private String especialidade;

    @Column(length = 45, nullable = false)
    private String crmv;

    @Column(length = 2, nullable = false)
    private String crmvUf;

}
