package br.com.sigvet.sigvetapi.feature.veterinarian.usecases;

import static br.com.sigvet.sigvetapi.common.utils.StringNormalizer.normalizeString;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.VeterinarianEntity;
import br.com.sigvet.sigvetapi.common.usecases.CreateUseCase;
import br.com.sigvet.sigvetapi.feature.user.usecases.UserValidateUseCase;
import br.com.sigvet.sigvetapi.feature.veterinarian.VeterinarianRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CreateVeterinarianUseCase implements CreateUseCase<VeterinarianEntity> {

    private final VeterinarianRepository repository;

    private final UserValidateUseCase userValidateUseCase;

    @Transactional
    @Override
    public VeterinarianEntity execute(VeterinarianEntity source) {
        Objects.requireNonNull(source);

        final var errors = userValidateUseCase.execute(source);

        if (repository.existsByCrmvAndCrmvUf(normalizeString(source.getCrmv()), normalizeString(source.getCrmvUf()))) {
            errors.add("CRMV is already being used");
        }

        if (!errors.isEmpty()) {
            throw new ApplicationException("Veterinarian invalid", errors);
        }
        
        return repository.findById(repository.save(source).getId()).get();
    }

}
