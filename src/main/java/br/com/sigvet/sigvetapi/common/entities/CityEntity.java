package br.com.sigvet.sigvetapi.common.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonFilter(CityEntity.CITY_ENTITY_FILTER_KEY)
@Entity
@Table(name = "cities")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class CityEntity extends BaseEntity<Long> {

    public static final String CITY_ENTITY_FILTER_KEY = "cityEntityFilter";

    @Column(length = 255, nullable = false)
    private String name;

    @JoinColumn(nullable = false)
    @ManyToOne
    private StateEntity state;
    
}
