package br.com.sigvet.sigvetapi.common.entities;

import br.com.sigvet.sigvetapi.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "cities")
@Data
@NoArgsConstructor
@SuperBuilder
public class CityEntity extends BaseEntity<Long> {

    @Column(length = 255, nullable = false)
    private String name;

    @JoinColumn(nullable = false)
    @ManyToOne
    private StateEntity state;
}
