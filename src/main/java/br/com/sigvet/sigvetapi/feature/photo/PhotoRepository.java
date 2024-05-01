package br.com.sigvet.sigvetapi.feature.photo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sigvet.sigvetapi.common.entities.PhotoEntity;
import br.com.sigvet.sigvetapi.common.entities.enums.EntityType;

public interface PhotoRepository extends JpaRepository<PhotoEntity, Long> {
    
    Optional<PhotoEntity> findByEntityIdAndEntityType(Long entityId, EntityType entityType);

    boolean existsByEntityIdAndEntityType(Long entityId, EntityType entityType);
}
