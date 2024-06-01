package br.com.sigvet.sigvetapi.feature.vaccination;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.sigvet.sigvetapi.common.entities.VaccinationEntity;



public interface VaccinationRepository extends JpaRepository<VaccinationEntity, Long>, JpaSpecificationExecutor<VaccinationEntity> {

    @Modifying
    int deleteByAnimalId(Long id);

    @Modifying
    int deleteByVaccineId(Long id);

    @Modifying
    int deleteByVeterinarianId(Long id);

    @Query(value = """
        SELECT v1.* FROM vaccinations v1
                INNER JOIN animals a ON a.id = v1.animal_id
                INNER JOIN veterinarians v2 ON v2.id = v1.veterinarian_id
                INNER JOIN clients c ON c.id = a.client_id
                INNER JOIN vaccines v3 ON v3.id = v1.vaccine_id
                INNER JOIN users u ON v2.id = u.id
                INNER JOIN users u2 ON u2.id = c.id
        WHERE
            (
                a.deleted IS FALSE AND
                u.deleted IS FALSE AND
                v3.deleted IS FALSE AND
                v1.deleted IS FALSE
            ) AND 
            (
                LOWER(unaccent(a.name)) LIKE unaccent(CONCAT('%', LOWER(?1), '%')) OR
                LOWER(unaccent(a.breed)) LIKE unaccent(CONCAT('%', LOWER(?1), '%')) OR
                LOWER(unaccent(u.name)) LIKE unaccent(CONCAT('%', LOWER(?1), '%')) OR
                LOWER(unaccent(v3.name)) LIKE unaccent(CONCAT('%', LOWER(?1), '%')) OR
                LOWER(unaccent(v3.lot)) LIKE unaccent(CONCAT('%', LOWER(?1), '%')) OR
                LOWER(unaccent(u.name)) LIKE unaccent(CONCAT('%', LOWER(?1), '%')) OR
                LOWER(unaccent(u2.name)) LIKE unaccent(CONCAT('%', LOWER(?1), '%')) 
            );
    """, nativeQuery = true)
    List<VaccinationEntity> search(String term);

    List<VaccinationEntity> findByAnimalClientId(Long id);

    @Query("SELECT v FROM VaccinationEntity v WHERE v.date >= :initialDate AND v.date <= :finalDate")
    List<VaccinationEntity> findAllByDataBetween(LocalDate initialDate, LocalDate finalDate);

}


