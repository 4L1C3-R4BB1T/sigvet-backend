package br.com.sigvet.sigvetapi.common.entities;

import br.com.sigvet.sigvetapi.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "states")
@Data
@NoArgsConstructor
@SuperBuilder
public class StateEntity extends BaseEntity<String> {

    @Column(length = 255, nullable = false)
    private String name;
}
