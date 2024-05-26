package br.com.sigvet.sigvetapi.common.entities;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonFilter(ClientEntity.CLIENT_ENTITY_FILTER_KEY)
@Entity
@Table(name = "clients")
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name = "id")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id = ?")
@SQLRestriction("deleted is false")
public class ClientEntity extends UserEntity  {

    public static final String CLIENT_ENTITY_FILTER_KEY = "clientEntityFilter";

    @SQLRestriction("deleted is false")
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<AnimalEntity> animals;
    
}
