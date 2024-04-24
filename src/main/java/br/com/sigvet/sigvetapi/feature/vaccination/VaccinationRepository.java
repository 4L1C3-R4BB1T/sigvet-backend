package br.com.sigvet.sigvetapi.feature.vaccination;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.sigvet.sigvetapi.common.entities.VaccinationEntity;

public interface VaccinationRepository extends JpaRepository<VaccinationEntity, Long>, JpaSpecificationExecutor<VaccinationEntity> {
    
    @Query("SELECT v FROM VaccinationEntity v WHERE v.dateTime >= :initialDate AND v.dateTime <= :finalDate")
    List<VaccinationEntity> findAllByDataBetween(LocalDateTime initialDate, LocalDateTime finalDate);

}
