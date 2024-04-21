package br.com.sigvet.sigvetapi.common;

import br.com.sigvet.sigvetapi.common.entities.AddressEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@SuperBuilder
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
    protected String cpf;

    @Column(length = 18, nullable = true)
    protected String phone;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AddressEntity address;
}
