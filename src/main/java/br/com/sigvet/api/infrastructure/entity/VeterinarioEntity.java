package br.com.sigvet.api.infrastructure.entity;

import jakarta.persistence.Entity;
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
public class VeterinarioEntity extends UsuarioEntity {
    
    private String especialidade;

    private String crmv;

    private String crmvUf;
}
