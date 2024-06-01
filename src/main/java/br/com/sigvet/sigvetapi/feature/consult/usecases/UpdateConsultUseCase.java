package br.com.sigvet.sigvetapi.feature.consult.usecases;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.ConsultEntity;
import br.com.sigvet.sigvetapi.common.entities.enums.ConsultationStatus;
import br.com.sigvet.sigvetapi.common.usecases.UpdateUseCase;
import br.com.sigvet.sigvetapi.feature.consult.ConsultMapper;
import br.com.sigvet.sigvetapi.feature.consult.ConsultRepository;
import br.com.sigvet.sigvetapi.feature.veterinarian.VeterinarianRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateConsultUseCase implements UpdateUseCase<ConsultEntity> {

    private final ConsultRepository repository;

    private final ConsultMapper consultMapper;

    private final VeterinarianRepository veterinarianRepository;

    @Override
    public void execute(Long id, ConsultEntity source) {
        final var consultOptional = repository.findById(Objects.requireNonNull(id));

        List<String> errors = new ArrayList<>();

        if (consultOptional.isEmpty()) {
            throw new ApplicationException("Consulta com id %d não encontrado".formatted(id));
        }

        if (Objects.nonNull(source.getHour()) && !(source.getHour().isAfter(LocalTime.of(7, 59)) && source.getHour().isBefore(LocalTime.of(19, 0)))) {
            throw new ApplicationException("Consult Invalid", List.of("O horário deve estar entre 8:00 horas e 18:59 horas"));
        }

        // Caso não haja horário disponível para o veterinário escolhido será lançada uma mensagem informativa
        final var veterinarianId = source.getVeterinarian().getId();
        final var veterinarian = veterinarianRepository.findById(veterinarianId).get();
        final var consultExists = repository.findByDateAndHourAndVeterinarianId(source.getDate(), source.getHour(), veterinarianId);

        if (consultExists.isPresent() &&  consultExists.get().getId() != id && consultExists.get().getVeterinarian().getId() == veterinarianId) {
            throw new ApplicationException("Consulta com data " + source.getDate() + " e horário "
                    + source.getHour() + " para o veterinário " + veterinarian.getName() + " já existem nesse horário.");
        }


        // Se a data for diferente da data que está lá, ela precisará estar no futuro

        final var consult = consultOptional.get();

        if (!consult.getDate().isEqual(source.getDate()) && source.getDate().isBefore(LocalDate.now())) {
            errors.add("Ao alterar a data, é necessário que ela esteja no futuro ou no presente.");
        }
        
        if (consult.getStatus().equals(ConsultationStatus.COMPLETED) || consult.getStatus().equals(ConsultationStatus.CANCELED)) {
            errors.add("A consulta já foi encerrada, não é possível alterar o status");
        }


        if (consult.getAnimal().getId() != source.getAnimal().getId()) {
            errors.add("Não é permitido alterar o animal");
        }
        
        if (!errors.isEmpty()) {
            throw new ApplicationException("Consult invalid", errors);
        }

        consultMapper.map(consult, source);
        repository.save(consult);
    }

}
