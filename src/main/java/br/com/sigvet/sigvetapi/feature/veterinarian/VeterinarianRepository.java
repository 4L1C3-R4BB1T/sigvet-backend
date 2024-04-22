package br.com.sigvet.sigvetapi.feature.veterinarian;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.sigvet.sigvetapi.common.entities.VeterinarianEntity;

public interface VeterinarianRepository extends JpaRepository<VeterinarianEntity, Long>, JpaSpecificationExecutor<VeterinarianEntity> {
   
    Optional<VeterinarianEntity> findByIdAndDeleted(Long id, boolean deleted);

    boolean existsByCrmvAndCrmvUf(String crmv, String crmvUf);

    default Optional<VeterinarianEntity> findByIdAndNotMarkedAsDeleted(Long id) {
        return findByIdAndDeleted(id, false);
    }

}
