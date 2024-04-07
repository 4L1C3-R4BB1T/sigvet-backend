package br.com.sigvet.api.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.sigvet.api.infrastructure.entity.ClienteEntity;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Long>, JpaSpecificationExecutor<ClienteEntity> {

    @Query("SELECT c FROM ClienteEntity c LEFT JOIN FETCH c.endereco e WHERE c.id = ?1 AND c.deleted = false")
    ClienteEntity findClienteByIdAndNotDeleted(Long id);

    @Query("SELECT c FROM ClienteEntity c LEFT JOIN FETCH c.endereco e LEFT JOIN c.animais a WHERE c.deleted = false AND a.id = ?1")
    Optional<ClienteEntity> findByAnimal(Long id);
    
}

