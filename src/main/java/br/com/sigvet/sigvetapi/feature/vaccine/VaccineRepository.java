package br.com.sigvet.sigvetapi.feature.vaccine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.sigvet.sigvetapi.common.entities.VaccineEntity;

public interface VaccineRepository extends JpaRepository<VaccineEntity, Long>, JpaSpecificationExecutor<VaccineEntity> {
}
