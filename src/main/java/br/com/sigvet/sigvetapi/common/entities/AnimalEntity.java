package br.com.sigvet.sigvetapi.common.entities;

import br.com.sigvet.sigvetapi.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDate;

@Entity
@Table(name = "animals")
@Data
@NoArgsConstructor
@SuperBuilder
@SQLDelete(sql = "UPDATE animals SET deleted = true WHERE id = ?")
public class AnimalEntity extends BaseEntity<Long> {

    @Column(length = 255, nullable = false)
    private String name;

    @Column(length = 255)
    private String breed;

    private LocalDate birthDate;

    @JoinColumn(nullable = false)
    @ManyToOne
    private ClientEntity client;
}
