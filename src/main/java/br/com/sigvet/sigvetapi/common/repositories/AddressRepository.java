package br.com.sigvet.sigvetapi.common.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.sigvetapi.common.entities.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM addresses a WHERE a.user_id = ?1", nativeQuery = true)
    void deleteByUserId(Long id);

    
}
