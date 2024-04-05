package br.com.sigvet.api.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sigvet.api.infrastructure.entity.CidadeEntity;


public interface CidadeJpaRepository extends JpaRepository<CidadeEntity, Long> {

    @Query("SELECT c FROM CidadeEntity c WHERE c.nome = :nome AND c.uf.sigla = :sigla")
    Optional<CidadeEntity> findByNomeAndSiglaUf(String nome, String sigla);
}
