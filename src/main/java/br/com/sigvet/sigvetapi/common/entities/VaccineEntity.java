package br.com.sigvet.sigvetapi.common.entities;

import br.com.sigvet.sigvetapi.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Table(name = "vaccines")
@Data
@NoArgsConstructor
@SuperBuilder
@SQLDelete(sql = "UPDATE vaccines SET deleted = true WHERE id = ?")
public class VaccineEntity extends BaseEntity<Long> {

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
