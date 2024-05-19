package br.com.sigvet.sigvetapi.common.entities;

import java.time.LocalDate;

import org.hibernate.annotations.SQLDelete;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonFilter(AnimalEntity.ANIMAL_ENTITY_FILTER_KEY)
@Entity
@Table(name = "animals")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@SuperBuilder
@SQLDelete(sql = "UPDATE animals SET deleted = true WHERE id = ?")
public class AnimalEntity extends BaseEntity<Long> {

    public static final String ANIMAL_ENTITY_FILTER_KEY = "animalEntityFilter";

    @Column(length = 255, nullable = false)
    private String name;

    @Column(length = 255)
    private String breed;

    private LocalDate birthDate;

    @Transient
    private String photoUrl;

    @JoinColumn(nullable = false)
    @ManyToOne
    private ClientEntity client;
    
}
