package br.com.sigvet.sigvetapi.common.entities;

import org.hibernate.annotations.SQLDelete;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonFilter(VeterinarianEntity.VETERINARIAN_ENTITY_FILTER_KEY)
@Entity
@Table(name = "veterinarians")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@PrimaryKeyJoinColumn(name = "id")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id = ?")
public class VeterinarianEntity extends UserEntity  {

    public static final String VETERINARIAN_ENTITY_FILTER_KEY = "veterinarianEntityFilter";

    @Column(length = 255, nullable = false)
    private String specialty;

    @Column(length = 45, nullable = false)
    private String crmv;

    @Column(length = 2, nullable = false)
    private String crmvUf;
    
}
