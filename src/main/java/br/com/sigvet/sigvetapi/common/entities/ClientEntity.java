package br.com.sigvet.sigvetapi.common.entities;

import br.com.sigvet.sigvetapi.common.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@SuperBuilder
@PrimaryKeyJoinColumn(name = "id")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id = ?")
public class ClientEntity extends UserEntity  {

    @SQLRestriction("deleted is false")
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<AnimalEntity> animals;
}
