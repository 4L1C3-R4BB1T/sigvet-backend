package br.com.sigvet.sigvetapi.feature.animal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Long>, JpaSpecificationExecutor<AnimalEntity> {

    @Query(value = "SELECT * FROM animals a WHERE (LOWER(unaccent(a.name)) LIKE unaccent(CONCAT('%', LOWER(?1), '%')) OR LOWER(unaccent(a.breed)) LIKE unaccent(CONCAT('%', LOWER(?1), '%'))) AND a.deleted is false", nativeQuery = true)
    List<AnimalEntity> searchByName(String name);

    List<AnimalEntity> findByClientIdAndDeleted(Long id, boolean deleted);

    default List<AnimalEntity> findByClientId(Long id) {
        return findByClientIdAndDeleted(id, false);
    }
}
