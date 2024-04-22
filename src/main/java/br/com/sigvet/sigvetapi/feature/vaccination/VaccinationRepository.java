package br.com.sigvet.sigvetapi.feature.vaccination;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.sigvet.sigvetapi.common.entities.VaccinationEntity;

public interface VaccinationRepository extends JpaRepository<VaccinationEntity, Long>, JpaSpecificationExecutor<VaccinationEntity> {
}
