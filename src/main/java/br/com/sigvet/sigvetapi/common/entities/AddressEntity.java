package br.com.sigvet.sigvetapi.common.entities;

import br.com.sigvet.sigvetapi.common.BaseEntity;
import br.com.sigvet.sigvetapi.common.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@SuperBuilder
@SQLDelete(sql = "UPDATE addresses SET deleted = true WHERE id = ?")
public class AddressEntity extends BaseEntity<Long> {

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
