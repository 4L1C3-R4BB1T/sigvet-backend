package br.com.sigvet.sigvetapi.feature.veterinarian.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.VeterinarianEntity;
import br.com.sigvet.sigvetapi.common.usecases.FindByIdUseCase;
import br.com.sigvet.sigvetapi.feature.veterinarian.VeterinarianRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindVeterinarianByIdUseCase implements FindByIdUseCase<VeterinarianEntity> {

    private final VeterinarianRepository repository;

    @Override
    public VeterinarianEntity execute(Long id) {
        return repository.findById(Objects.requireNonNull(id))
            .orElseThrow(() -> new ApplicationException("Veterinarian with id %d not found".formatted(id)));
    }
    
}
