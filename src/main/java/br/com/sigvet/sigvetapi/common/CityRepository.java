package br.com.sigvet.sigvetapi.common;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sigvet.sigvetapi.common.entities.CityEntity;

public interface CityRepository extends JpaRepository<CityEntity, Long> {
    
    boolean existsByNameAndStateId(String name, String state); 
}
