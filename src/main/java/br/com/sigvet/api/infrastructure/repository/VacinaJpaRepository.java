package br.com.sigvet.api.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.sigvet.api.infrastructure.entity.VacinaEntity;

public interface VacinaJpaRepository extends JpaRepository<VacinaEntity, Long>, JpaSpecificationExecutor<VacinaEntity> {

    @Query("SELECT ve FROM VacinaEntity ve WHERE ve.id = ?1 AND ve.deleted = false")
    VacinaEntity findVacinaByIdAndNotDeleted(Long id);
}
