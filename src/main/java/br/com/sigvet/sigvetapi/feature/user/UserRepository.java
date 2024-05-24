package br.com.sigvet.sigvetapi.feature.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sigvet.sigvetapi.common.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email); 

    boolean existsByUsername(String username);

    boolean existsByDocument(String document);

    boolean existsByEmailAndDocument(String email, String document);

    Optional<UserEntity> findByEmailOrUsername(String email, String username);

    Optional<UserEntity> findByEmail(String email);
    
}
