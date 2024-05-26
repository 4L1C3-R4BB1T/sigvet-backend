package br.com.sigvet.sigvetapi.feature.vaccination.usecases;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.VaccinationEntity;
import br.com.sigvet.sigvetapi.common.usecases.UpdateUseCase;
import br.com.sigvet.sigvetapi.feature.vaccination.VaccinationMapper;
import br.com.sigvet.sigvetapi.feature.vaccination.VaccinationRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateVaccinationUseCase implements UpdateUseCase<VaccinationEntity> {

    private final VaccinationRepository repository;

    private final VaccinationMapper vaccinationMapper;

    @Transactional
    @Override
    public void execute(Long id, VaccinationEntity source) {
        System.out.println("oii eu entrei aqui");
        final var vaccinationOptional = repository.findById(Objects.requireNonNull(id));

        List<String> errors = new ArrayList<>();

        if (vaccinationOptional.isEmpty()) {
            throw new ApplicationException("Vacinação com id %d não encontrada".formatted(id));
        }

        final var vaccination = vaccinationOptional.get();

        if (Objects.nonNull(source.getHour()) && !(source.getHour().isAfter(LocalTime.of(8, 0)) && source.getHour().isBefore(LocalTime.of(19, 0)))) {
            throw new ApplicationException("Vaccination Invalid", List.of("O horário deve estar entre 8:00 horas e 18:59 horas"));
        }

        // if (vaccination.getVaccine().getId() != source.getVaccine().getId()) {
        //     errors.add("A vacina não pode ser alterada");
        // }

        // if (vaccination.getVeterinarian().getId() != source.getVeterinarian().getId()) {
        //     errors.add("O veterinário não pode ser alterado");
        // }

        if (vaccination.getAnimal().getId() != source.getAnimal().getId()) {
            errors.add("O animal não pode ser alterado");
        }

        if (!errors.isEmpty()) {
            throw new ApplicationException("Vaccination invalid", errors);
        }

        vaccinationMapper.map(vaccination, source);
        repository.save(vaccination);
    }

}
