package br.com.sigvet.sigvetapi.common;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sigvet.sigvetapi.common.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email); 

    boolean existsByUsername(String username);

    boolean existsByDocument(String document);
}
