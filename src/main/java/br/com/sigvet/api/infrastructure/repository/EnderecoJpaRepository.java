package br.com.sigvet.api.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.sigvet.api.infrastructure.entity.EnderecoEntity;

public interface EnderecoJpaRepository extends JpaRepository<EnderecoEntity, Long> {
    
    @Modifying
    @Query(value = "DELETE FROM enderecos e WHERE e.usuario_id = ?1", nativeQuery = true)
    void deleteEnderecoByIdUsuario(Long usuarioId);
}
