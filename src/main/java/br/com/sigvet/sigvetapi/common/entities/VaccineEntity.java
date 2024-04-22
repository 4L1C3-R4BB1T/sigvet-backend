package br.com.sigvet.sigvetapi.common.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.annotations.SQLDelete;

import com.fasterxml.jackson.annotation.JsonFilter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@JsonFilter(VaccineEntity.VACCINE_ENTITY_FILTER_KEY)
@Entity
@Table(name = "vaccines")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@SuperBuilder
@SQLDelete(sql = "UPDATE vaccines SET deleted = true WHERE id = ?")
public class VaccineEntity extends BaseEntity<Long> {

    public static final String VACCINE_ENTITY_FILTER_KEY = "vaccineEntityFilter";

    @Column(length = 255, nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private String manufacturer;

    @Column(length = 255, nullable = false)
    private String lot;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDate expirationDate;

}
