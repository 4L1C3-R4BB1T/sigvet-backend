package br.com.sigvet.sigvetapi.feature.vaccination.usecases;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.entities.VaccinationEntity;
import br.com.sigvet.sigvetapi.feature.vaccination.VaccinationRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SearchVaccinationUseCase {
    
    private final VaccinationRepository vaccinationRepository;

    public List<VaccinationEntity> execute(final String term) {
        return vaccinationRepository.search(term);
    }
}
