package br.com.sigvet.sigvetapi.feature.consult;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import br.com.sigvet.sigvetapi.common.entities.ConsultEntity;

public interface ConsultRepository extends JpaRepository<ConsultEntity, Long>, JpaSpecificationExecutor<ConsultEntity> {

    Optional<ConsultEntity> findByDateTimeAndVeterinarianId(LocalDateTime dateTime, Long veterinarianId);

}
