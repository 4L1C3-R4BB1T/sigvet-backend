package br.com.sigvet.sigvetapi.feature.vaccination.usecases;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.entities.VaccinationEntity;
import br.com.sigvet.sigvetapi.feature.vaccination.VaccinationRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class FindVaccinationByClientIdUseCase {

    private final VaccinationRepository vaccinationRepository;
    
    public List<VaccinationEntity> execute(Long id) {
        return vaccinationRepository.findByAnimalClientId(id);
    }
}
