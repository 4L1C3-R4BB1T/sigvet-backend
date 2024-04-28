package br.com.sigvet.sigvetapi.common.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonFilter(StateEntity.STATE_ENTITY_FILTER_KEY)
@Entity
@Table(name = "states")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@SuperBuilder
public class StateEntity extends BaseEntity<String> {

    public static final String STATE_ENTITY_FILTER_KEY = "stateEntityFilter";

    @Column(length = 255, nullable = false)
    private String name;
    
}
