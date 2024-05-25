package br.com.sigvet.sigvetapi.feature.veterinarian;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.sigvet.sigvetapi.common.entities.VeterinarianEntity;

public interface VeterinarianRepository extends JpaRepository<VeterinarianEntity, Long>, JpaSpecificationExecutor<VeterinarianEntity> {
   
    Optional<VeterinarianEntity> findByIdAndDeleted(Long id, boolean deleted);

    boolean existsByCrmvAndCrmvUf(String crmv, String crmvUf);

    default Optional<VeterinarianEntity> findByIdAndNotMarkedAsDeleted(Long id) {
        return findByIdAndDeleted(id, false);
    }

    @Query(value = "SELECT * FROM veterinarians v INNER JOIN users u ON u.id = v.id  WHERE LOWER(unaccent(u.name)) LIKE unaccent(CONCAT('%', LOWER(?1), '%')) AND u.deleted is false", nativeQuery = true)
    List<VeterinarianEntity> searchByName(String name);

}
