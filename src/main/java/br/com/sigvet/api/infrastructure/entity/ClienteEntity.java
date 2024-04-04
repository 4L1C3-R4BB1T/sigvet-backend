package br.com.sigvet.api.infrastructure.entity;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "clientes")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@PrimaryKeyJoinColumn(name = "id")
@SQLDelete(sql = "UPDATE usuarios SET deleted = true WHERE id = ?")
public class ClienteEntity extends UsuarioEntity {
    
    @SQLRestriction("deleted is false")
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<AnimalEntity> animais;
}
