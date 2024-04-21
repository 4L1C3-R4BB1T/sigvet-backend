package br.com.sigvet.sigvetapi.common.entities;

import br.com.sigvet.sigvetapi.common.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "veterinarians")
@Data
@NoArgsConstructor
@SuperBuilder
@PrimaryKeyJoinColumn(name = "id")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id = ?")
public class VeterinarianEntity extends UserEntity  {

    @Column(length = 255, nullable = false)
    private String specialty;

    @Column(length = 45, nullable = false)
    private String crmv;

    @Column(length = 2, nullable = false)
    private String crmvUf;
}
