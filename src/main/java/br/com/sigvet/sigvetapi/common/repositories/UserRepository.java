package br.com.sigvet.sigvetapi.common.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sigvet.sigvetapi.common.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email); 

    boolean existsByUsername(String username);

    boolean existsByDocument(String document);

    Optional<UserEntity> findByEmail(String email);
    
}
