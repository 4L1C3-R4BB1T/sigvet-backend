package br.com.sigvet.api.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.sigvet.api.infrastructure.entity.AnimalEntity;

public interface AnimalJpaRepository extends JpaRepository<AnimalEntity, Long>, JpaSpecificationExecutor<AnimalEntity> {

    @Query("SELECT c FROM AnimalEntity c JOIN FETCH c.cliente e WHERE c.id = ?1 AND c.deleted = false")
    AnimalEntity findAnimalByIdAndNotDeleted(Long id);
    
}

