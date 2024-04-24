package br.com.sigvet.sigvetapi.feature.consult;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.sigvet.sigvetapi.common.entities.ConsultEntity;
import br.com.sigvet.sigvetapi.common.entities.enums.ConsultationStatus;

public interface ConsultRepository extends JpaRepository<ConsultEntity, Long>, JpaSpecificationExecutor<ConsultEntity> {

    Optional<ConsultEntity> findByDateTimeAndVeterinarianId(LocalDateTime dateTime, Long veterinarianId);

    Optional<ConsultEntity> findByIdAndStatus(Long id, ConsultationStatus status);

    default Optional<ConsultEntity> findByIdAndStatusCompleted(Long id) {
        return findByIdAndStatus(id, ConsultationStatus.COMPLETED);
    }

}
