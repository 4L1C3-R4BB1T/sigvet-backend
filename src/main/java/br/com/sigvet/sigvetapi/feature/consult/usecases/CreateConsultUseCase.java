package br.com.sigvet.sigvetapi.feature.consult.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.ConsultEntity;
import br.com.sigvet.sigvetapi.common.usecases.CreateUseCase;
import br.com.sigvet.sigvetapi.feature.animal.AnimalRepository;
import br.com.sigvet.sigvetapi.feature.consult.ConsultRepository;
import br.com.sigvet.sigvetapi.feature.veterinarian.VeterinarianRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateConsultUseCase implements CreateUseCase<ConsultEntity> {

    private final ConsultRepository repository;

    private final AnimalRepository animalRepository;
    private final VeterinarianRepository veterinarianRepository;

    @Transactional
    @Override
    public ConsultEntity execute(ConsultEntity source) {
        final var animalId = source.getAnimal().getId();

        if (!animalRepository.existsById(animalId)) {
            throw new ApplicationException("Animal with id %d not found".formatted(animalId));
        }

        final var veterinarianId = source.getVeterinarian().getId();

        if (!veterinarianRepository.existsById(veterinarianId)) {
            throw new ApplicationException("Veterinarian with id %d not found".formatted(veterinarianId));
        }

        // Caso não haja horário disponível para o veterinário escolhido será lançada uma mensagem informativa
        final var consultOptional = repository.findByDateTimeAndVeterinarianId(source.getDateTime(), veterinarianId);

        if (consultOptional.isPresent() && consultOptional.get().getVeterinarian().getId() == veterinarianId) {
            throw new ApplicationException("Consult with date time " + source.getDateTime()
                    + " and veterinarian id " + veterinarianId + " already exists");
        }

        return repository.save(Objects.requireNonNull(source));
    }

}
