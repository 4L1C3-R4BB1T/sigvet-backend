package br.com.sigvet.sigvetapi.feature.veterinarian.usecases;

import static br.com.sigvet.sigvetapi.common.utils.StringNormalizer.normalizeString;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.UserValidateUseCase;
import br.com.sigvet.sigvetapi.common.entities.VeterinarianEntity;
import br.com.sigvet.sigvetapi.common.repositories.CityRepository;
import br.com.sigvet.sigvetapi.common.repositories.UserRepository;
import br.com.sigvet.sigvetapi.common.usecases.CreateUseCase;
import br.com.sigvet.sigvetapi.feature.veterinarian.VeterinarianRepository;

@Component
public class CreateVeterinarianUseCase extends UserValidateUseCase implements CreateUseCase<VeterinarianEntity> {

    private final VeterinarianRepository repository;

    public CreateVeterinarianUseCase(UserRepository userRepository, CityRepository cityRepository,
            VeterinarianRepository repository) {
        super(userRepository, cityRepository);
        this.repository = repository;
    }

    @Transactional
    @Override
    public VeterinarianEntity execute(VeterinarianEntity source) {
        Objects.requireNonNull(source);

        final var errors = validateOnCreate(source);

        if (repository.existsByCrmvAndCrmvUf(normalizeString(source.getCrmv()), normalizeString(source.getCrmvUf()))) {
            errors.add("CRMV is already being used");
        }

        if (!errors.isEmpty()) {
            throw new ApplicationException("Veterinarian invalid", errors);
        }
      

        return repository.save(source);
    }

}
