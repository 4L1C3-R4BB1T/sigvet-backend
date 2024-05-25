package br.com.sigvet.sigvetapi.feature.vaccine.usecases;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.entities.VaccineEntity;
import br.com.sigvet.sigvetapi.feature.vaccine.VaccineRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SearchVaccineByNameUseCase {
    
    private final VaccineRepository repository;

    public List<VaccineEntity> execute(String name) {
        return repository.searchByName(name);
    }
}
