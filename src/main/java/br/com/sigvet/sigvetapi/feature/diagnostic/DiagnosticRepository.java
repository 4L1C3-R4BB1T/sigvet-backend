package br.com.sigvet.sigvetapi.feature.diagnostic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.sigvet.sigvetapi.common.entities.DiagnosticEntity;

public interface DiagnosticRepository extends JpaRepository<DiagnosticEntity, Long>, JpaSpecificationExecutor<DiagnosticEntity> {

}
