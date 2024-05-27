package br.com.sigvet.sigvetapi.feature.consult.usecases;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.ConsultEntity;
import br.com.sigvet.sigvetapi.common.usecases.UpdateUseCase;
import br.com.sigvet.sigvetapi.feature.consult.ConsultMapper;
import br.com.sigvet.sigvetapi.feature.consult.ConsultRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateConsultUseCase implements UpdateUseCase<ConsultEntity> {

    private final ConsultRepository repository;

    private final ConsultMapper consultMapper;

    @Override
    public void execute(Long id, ConsultEntity source) {
        final var consultOptional = repository.findById(Objects.requireNonNull(id));

        List<String> errors = new ArrayList<>();

        if (consultOptional.isEmpty()) {
            throw new ApplicationException("Consulta com id %d não encontrado".formatted(id));
        }

        if (Objects.nonNull(source.getHour()) && !(source.getHour().isAfter(LocalTime.of(8, 0)) && source.getHour().isBefore(LocalTime.of(19, 0)))) {
            throw new ApplicationException("Consult Invalid", List.of("O horário deve estar entre 8:00 horas e 18:59 horas"));
        }

        // Caso não haja horário disponível para o veterinário escolhido será lançada uma mensagem informativa
        final var veterinarianId = source.getVeterinarian().getId();
        final var consultExists = repository.findByDateAndHourAndVeterinarianId(source.getDate(), source.getHour(), veterinarianId);

        if (consultExists.isPresent() && consultExists.get().getVeterinarian().getId() == veterinarianId) {
            throw new ApplicationException("Consulta com data " + source.getDate() + " e horário "
                    + source.getHour() + " e veterinário com id " + veterinarianId + " já existem");
        }

        final var consult = consultOptional.get();

        if (consult.getVeterinarian().getId() != source.getVeterinarian().getId()) {
            errors.add("Não é possível alterar o veterinário");
        }

        if (consult.getAnimal().getId() != source.getAnimal().getId()) {
            errors.add("Não é possível alterar o animal");
        }
        
        if (!errors.isEmpty()) {
            throw new ApplicationException("Consult invalid", errors);
        }

        consultMapper.map(consult, source);
        repository.save(consult);
    }

}
