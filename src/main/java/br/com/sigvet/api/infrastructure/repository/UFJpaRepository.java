package br.com.sigvet.api.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sigvet.api.infrastructure.entity.UFEntity;

public interface UFJpaRepository extends JpaRepository<UFEntity, String> {
   
}
