package br.com.sigvet.api.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sigvet.api.infrastructure.entity.ClienteEntity;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Long>, JpaSpecificationExecutor<ClienteEntity> {
    
    @Query("SELECT c FROM ClienteEntity c WHERE c.id = :id AND c.deleted = false")
    ClienteEntity findClienteByIdAndNotDeleted(@Param("id") Long id);

}
