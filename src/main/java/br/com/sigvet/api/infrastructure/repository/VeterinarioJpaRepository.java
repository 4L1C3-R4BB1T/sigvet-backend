package br.com.sigvet.api.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sigvet.api.infrastructure.entity.VeterinarioEntity;

public interface VeterinarioJpaRepository extends JpaRepository<VeterinarioEntity, Long>, JpaSpecificationExecutor<VeterinarioEntity> {
    
    @Query("SELECT c FROM VeterinarioEntity c WHERE c.id = :id AND c.deleted = false")
    VeterinarioEntity findVeterinarioByIdAndNotDeleted(@Param("id") Long id);

}
