package br.com.sigvet.sigvetapi.common.entities;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id = ?")
@SQLRestriction("deleted is false")
public class UserEntity extends BaseEntity<Long> {

    @Column(length = 100, nullable = false)
    protected String username;

    @Column(length = 100, nullable = false, unique = true)
    protected String email;

    @Column(length = 100, nullable = false)
    protected String password;

    @Column(length = 100, nullable = false)
    protected String name;

    @Column(length = 14, nullable = false)
    protected String document;

    @Column(length = 18, nullable = true)
    protected String phone;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AddressEntity address;
}
