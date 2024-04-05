package br.com.sigvet.api.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.sigvet.api.infrastructure.entity.VeterinarioEntity;

public interface VeterinarioJpaRepository extends JpaRepository<VeterinarioEntity, Long>, JpaSpecificationExecutor<VeterinarioEntity> {
    
    @Query("SELECT ve FROM VeterinarioEntity ve LEFT JOIN FETCH ve.endereco e WHERE ve.id = ?1 AND ve.deleted = false")
    VeterinarioEntity findVeterinarioByIdAndNotDeleted(Long id);

    Optional<VeterinarioEntity> findByCrmvAndCrmvUf(String crmv, String crmvUf);
}
