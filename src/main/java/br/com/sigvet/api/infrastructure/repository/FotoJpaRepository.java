package br.com.sigvet.api.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sigvet.api.infrastructure.entity.FotoEntity;


// @Column(nullable = false)
// private Long entidadeId;

// @Column(nullable = false)
// private String entidadeTipo;
public interface FotoJpaRepository extends JpaRepository<FotoEntity, Long> {
    
    Optional<FotoEntity> queryByEntidadeId(Long id);

    Optional<FotoEntity> queryByEntidadeIdAndEntidadeTipo(Long entidadeId, String entidadeTipo);
}
