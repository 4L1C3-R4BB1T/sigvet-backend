package br.com.sigvet.sigvetapi.feature.veterinarian.usecases;

import static br.com.sigvet.sigvetapi.common.utils.StringNormalizer.normalizeString;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.VeterinarianEntity;
import br.com.sigvet.sigvetapi.common.repositories.AddressRepository;
import br.com.sigvet.sigvetapi.common.usecases.UpdateUseCase;
import br.com.sigvet.sigvetapi.feature.user.usecases.UserValidateUseCase;
import br.com.sigvet.sigvetapi.feature.veterinarian.VeterinarianMapper;
import br.com.sigvet.sigvetapi.feature.veterinarian.VeterinarianRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UpdateVeterinarianUseCase implements UpdateUseCase<VeterinarianEntity> {

    private final VeterinarianRepository repository;

    private final AddressRepository addressRepository;

    private final VeterinarianMapper veterinarianMapper;

    private final UserValidateUseCase userValidateUseCase;

    @Transactional
    @Override
    public void execute(Long id, VeterinarianEntity source) {
        final var veterinarianOptional = repository.findById(Objects.requireNonNull(id));

        if (veterinarianOptional.isEmpty()) {
            throw new ApplicationException("Veterinarian with id %d not found".formatted(id));
        }

        final var veterinarian = veterinarianOptional.get();

        if (Objects.nonNull(veterinarian.getAddress())) {
            addressRepository.deleteByUserId(id);
        }

        final var errors = userValidateUseCase.execute(veterinarian, source);

        if (!(normalizeString(veterinarian.getCrmv()).equals(normalizeString(source.getCrmv())) &&
            normalizeString(veterinarian.getCrmvUf()).equals(normalizeString(source.getCrmvUf()))) &&
            repository.existsByCrmvAndCrmvUf(source.getCrmv(), source.getCrmvUf())) {
            errors.add("CRMV is already being used");
        }

        if (!errors.isEmpty()) {
            throw new ApplicationException("Veterinarian Invalid", errors);
        }
        

        veterinarianMapper.map(veterinarian, source);
        repository.save(veterinarian);
    }

}
