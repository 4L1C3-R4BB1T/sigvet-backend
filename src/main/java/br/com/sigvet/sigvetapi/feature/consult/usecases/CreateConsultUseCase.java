package br.com.sigvet.sigvetapi.feature.consult.usecases;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.ConsultEntity;
import br.com.sigvet.sigvetapi.common.entities.enums.ConsultationStatus;
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

        if (Objects.nonNull(source.getHour()) && !(source.getHour().isAfter(LocalTime.of(8, 0)) && source.getHour().isBefore(LocalTime.of(19, 0)))) {
            throw new ApplicationException("Consult Invalid", List.of("O horário deve estar entre 8:00 horas e 18:59 horas"));
        }

        if (!animalRepository.existsById(animalId)) {
            throw new ApplicationException("Animal com id %d não encontrado".formatted(animalId));
        }

        final var veterinarianId = source.getVeterinarian().getId();

        if (!veterinarianRepository.existsById(veterinarianId)) {
            throw new ApplicationException("Veterinário com id %d não encontrado".formatted(veterinarianId));
        }

        // Caso não haja horário disponível para o veterinário escolhido será lançada uma mensagem informativa
        final var consultOptional = repository.findByDateAndHourAndVeterinarianId(source.getDate(), source.getHour(), veterinarianId);

        if (consultOptional.isPresent() && consultOptional.get().getVeterinarian().getId() == veterinarianId) {
            throw new ApplicationException("Consulta com data " + source.getDate() + " e horário "
                    + source.getHour() + " e veterinário com id " + veterinarianId + " já existem");
        }

        source.setStatus(ConsultationStatus.SCHEDULED);
        return repository.save(Objects.requireNonNull(source));
    }

}
