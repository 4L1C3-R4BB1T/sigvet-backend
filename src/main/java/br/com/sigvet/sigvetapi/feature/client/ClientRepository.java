package br.com.sigvet.sigvetapi.feature.client;

import br.com.sigvet.sigvetapi.common.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long>, JpaSpecificationExecutor<ClientEntity> {
    
    Optional<ClientEntity> findByIdAndDeleted(Long id, boolean deleted);

    default Optional<ClientEntity> findByIdAndNotMarkedAsDeleted(Long id) {
        return findByIdAndDeleted(id, false);
    }

}
