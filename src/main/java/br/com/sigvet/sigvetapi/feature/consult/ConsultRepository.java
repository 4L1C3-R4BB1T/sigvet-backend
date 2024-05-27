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

}
