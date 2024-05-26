package br.com.sigvet.sigvetapi.common.entities;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.sigvet.sigvetapi.common.converts.CEPConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonFilter(AddressEntity.ADDRESS_ENTITY_FILTER_KEY)
@Entity
@Table(name = "addresses")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@SuperBuilder
@SQLDelete(sql = "UPDATE addresses SET deleted = true WHERE id = ?")
@SQLRestriction("deleted is false")
public class AddressEntity extends BaseEntity<Long> {

    public static final String ADDRESS_ENTITY_FILTER_KEY = "addressEntityFilter";

    @Column(length = 255, nullable = false)
    private String street;

    @Column(length = 255, nullable = false)
    private String neighborhood;

    @Convert(converter = CEPConverter.class)
    @Column(length = 8, nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private Integer number;

    @JoinColumn(nullable = false)
    @ManyToOne
    private CityEntity city;

    @JoinColumn(nullable = false)
    @OneToOne
    private UserEntity user;
    
}
