package br.com.sigvet.sigvetapi.feature.vaccine;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.sigvet.sigvetapi.common.entities.VaccineEntity;

public interface VaccineRepository extends JpaRepository<VaccineEntity, Long>, JpaSpecificationExecutor<VaccineEntity> {

    @Query(value = "SELECT * FROM vaccines v WHERE v.deleted IS FALSE AND LOWER(unaccent(v.name)) LIKE unaccent(CONCAT('%', LOWER(?1), '%'))", nativeQuery = true)
    List<VaccineEntity> searchByName(String name);
}
 