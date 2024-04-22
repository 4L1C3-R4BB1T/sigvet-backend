package br.com.sigvet.sigvetapi.common.entities;

import org.hibernate.annotations.SQLDelete;

import com.fasterxml.jackson.annotation.JsonFilter;

import jakarta.persistence.Column;
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

@JsonFilter(AddressEntity.ADDRESS_ENTITY_FILTER_KEY)
@Entity
@Table(name = "addresses")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@SuperBuilder
@SQLDelete(sql = "UPDATE addresses SET deleted = true WHERE id = ?")
public class AddressEntity extends BaseEntity<Long> {

    public static final String ADDRESS_ENTITY_FILTER_KEY = "addressEntityFilter";

    @Column(length = 255, nullable = false)
    private String street;

    @Column(length = 255, nullable = false)
    private String neighborhood;

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
