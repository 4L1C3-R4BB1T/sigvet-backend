package br.com.sigvet.api.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.sigvet.api.infrastructure.entity.ConsultaEntity;

public interface ConsultaJpaRepository extends JpaRepository<ConsultaEntity, Long>, JpaSpecificationExecutor<ConsultaEntity> {

}
