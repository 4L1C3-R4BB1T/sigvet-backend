package br.com.sigvet.sigvetapi.feature.consult.usecases;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.ConsultEntity;
import br.com.sigvet.sigvetapi.common.usecases.UpdateUseCase;
import br.com.sigvet.sigvetapi.feature.consult.ConsultRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateConsultUseCase implements UpdateUseCase<ConsultEntity> {

    private final ConsultRepository repository;

    @Override
    public void execute(Long id, ConsultEntity source) {
        final var consultOptional = repository.findById(Objects.requireNonNull(id));

        List<String> errors = new ArrayList<>();

        if (consultOptional.isEmpty()) {
            throw new ApplicationException("Consult with id %d not found".formatted(id));
        }

        final var consult = consultOptional.get();

        if (consult.getVeterinarian().getId() != source.getVeterinarian().getId()) {
            errors.add("Cannot be changing the veterinarian id");
        }

        if (consult.getAnimal().getId() != source.getAnimal().getId()) {
            errors.add("Cannot be changing the animal id");
        }
        
        if (!errors.isEmpty()) {
            throw new ApplicationException("Consult invalid", errors);
        }

        repository.save(consult);
    }

}
