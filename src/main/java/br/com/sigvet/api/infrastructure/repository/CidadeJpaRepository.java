package br.com.sigvet.api.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sigvet.api.infrastructure.entity.CidadeEntity;

public interface CidadeJpaRepository extends JpaRepository<CidadeEntity, Long> {
}
