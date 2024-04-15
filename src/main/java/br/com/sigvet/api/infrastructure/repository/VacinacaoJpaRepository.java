package br.com.sigvet.api.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.sigvet.api.infrastructure.entity.VacinacaoEntity;

public interface VacinacaoJpaRepository extends JpaRepository<VacinacaoEntity, Long>, JpaSpecificationExecutor<VacinacaoEntity> {
    
}
