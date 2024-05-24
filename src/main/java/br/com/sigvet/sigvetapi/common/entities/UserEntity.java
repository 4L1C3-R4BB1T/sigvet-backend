package br.com.sigvet.sigvetapi.common.entities;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.sigvet.sigvetapi.common.converts.CPFConverter;
import br.com.sigvet.sigvetapi.common.entities.enums.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@JsonFilter(UserEntity.USER_ENTITY_FILTER_KEY)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id = ?")
@SQLRestriction("deleted is false")
public class UserEntity extends BaseEntity<Long> {
 
    public static final String USER_ENTITY_FILTER_KEY = "user";

    @Column(length = 100, nullable = false)
    protected String username;

    @Column(length = 100, nullable = false, unique = true)
    protected String email;

    @Column(length = 100, nullable = false)
    protected String password;

    @Column(length = 100, nullable = false)
    protected String name;

    @Convert(converter = CPFConverter.class)
    @Column(length = 14, nullable = false)
    protected String document;

    @Column(length = 18, nullable = true)
    protected String phone;

    @Transient
    protected String photoUrl;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AddressEntity address;

    @ElementCollection
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private List<Role> roles;
    
}
