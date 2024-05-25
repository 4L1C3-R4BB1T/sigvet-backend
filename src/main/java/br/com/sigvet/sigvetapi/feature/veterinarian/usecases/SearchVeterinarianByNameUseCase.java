package br.com.sigvet.sigvetapi.feature.veterinarian.usecases;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.entities.VeterinarianEntity;
import br.com.sigvet.sigvetapi.feature.veterinarian.VeterinarianRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SearchVeterinarianByNameUseCase {
    
    private final VeterinarianRepository repository;

    public List<VeterinarianEntity> execute(final String name) {
        return repository.searchByName(name);
    }
}


