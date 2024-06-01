package br.com.sigvet.sigvetapi.feature.consult;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.sigvet.sigvetapi.common.entities.ConsultEntity;
import br.com.sigvet.sigvetapi.common.entities.enums.ConsultationStatus;

public interface ConsultRepository extends JpaRepository<ConsultEntity, Long>, JpaSpecificationExecutor<ConsultEntity> {

    Optional<ConsultEntity> findByDateAndHourAndVeterinarianId(LocalDate date, LocalTime hour, Long veterinarianId);

    Optional<ConsultEntity> findByIdAndStatus(Long id, ConsultationStatus status);

    default Optional<ConsultEntity> findByIdAndStatusCompleted(Long id) {
        return findByIdAndStatus(id, ConsultationStatus.COMPLETED);
    }

    @Query("SELECT c FROM ConsultEntity c WHERE c.date >= :initialDate AND c.date <= :finalDate")
    List<ConsultEntity> findAllByDataBetween(LocalDate initialDate, LocalDate finalDate);

    @Query("SELECT c FROM ConsultEntity c WHERE c.veterinarian.id = :id AND c.date >= :initialDate AND c.date <= :finalDate")
    List<ConsultEntity> findAllByVeterinarianAndDataBetween(LocalDate initialDate, LocalDate finalDate, Long id);

    @Query(
        value = """
            SELECT c.*
            FROM consults c
            INNER JOIN veterinarians v ON v.id = c.veterinarian_id
            INNER JOIN animals a ON a.id = c.animal_id 
            INNER JOIN users u ON u.id = v.id 
            INNER JOIN users u2 ON u2.id = a.client_id
            WHERE 
                c.deleted IS FALSE 
            AND 
                (
                    LOWER(unaccent(u.name)) LIKE unaccent(CONCAT('%', LOWER(?1), '%'))
                    OR
                    LOWER(unaccent(v.specialty)) LIKE unaccent(CONCAT('%', LOWER(?1), '%'))
                    OR
                    LOWER(unaccent(a.name)) LIKE unaccent(CONCAT('%', LOWER(?1), '%'))
                    OR
                    LOWER(unaccent(a.breed)) LIKE unaccent(CONCAT('%', LOWER(?1), '%'))
                    OR
                    LOWER(unaccent(u2.name)) LIKE unaccent(CONCAT('%', LOWER(?1), '%'))
                    OR
                    LOWER(unaccent(c.status)) LIKE unaccent(CONCAT('%', LOWER(?1), '%'))
                );
            
            """,
            nativeQuery = true
    )
    List<ConsultEntity> search(String term);

}
