package br.com.sigvet.sigvetapi.feature.client;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.sigvet.sigvetapi.common.entities.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long>, JpaSpecificationExecutor<ClientEntity> {
    
    Optional<ClientEntity> findByIdAndDeleted(Long id, boolean deleted);

    default Optional<ClientEntity> findByIdAndNotMarkedAsDeleted(Long id) {
        return findByIdAndDeleted(id, false);
    }

    @Query(value = "SELECT * FROM clients c INNER JOIN users u ON u.id = c.id WHERE LOWER(unaccent(u.name)) LIKE unaccent(CONCAT('%', LOWER(?1), '%')) AND u.deleted = false", nativeQuery = true)
    List<ClientEntity> searchByName(String name);

}
