package br.com.sigvet.sigvetapi.feature.vaccination.usecases;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

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

    @Override
    public void execute(Long id, VaccinationEntity source) {
        final var vaccinationOptional = repository.findById(Objects.requireNonNull(id));

        List<String> errors = new ArrayList<>();

        if (vaccinationOptional.isEmpty()) {
            throw new ApplicationException("Vaccination with id %d not found".formatted(id));
        }

        final var vaccination = vaccinationOptional.get();

        if (vaccination.getVaccine().getId() != source.getVaccine().getId()) {
            errors.add("Cannot be changing the vaccine id");
        }

        if (vaccination.getVeterinarian().getId() != source.getVeterinarian().getId()) {
            errors.add("Cannot be changing the veterinarian id");
        }

        if (vaccination.getAnimal().getId() != source.getAnimal().getId()) {
            errors.add("Cannot be changing the animal id");
        }

        if (!errors.isEmpty()) {
            throw new ApplicationException("Vaccination invalid", errors);
        }

        vaccinationMapper.map(vaccination, source);
        repository.save(vaccination);
    }

}
