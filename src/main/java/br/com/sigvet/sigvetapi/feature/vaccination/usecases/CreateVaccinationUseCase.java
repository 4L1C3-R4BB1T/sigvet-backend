package br.com.sigvet.sigvetapi.feature.vaccination.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.VaccinationEntity;
import br.com.sigvet.sigvetapi.common.usecases.CreateUseCase;
import br.com.sigvet.sigvetapi.feature.animal.AnimalRepository;
import br.com.sigvet.sigvetapi.feature.vaccination.VaccinationRepository;
import br.com.sigvet.sigvetapi.feature.vaccine.VaccineRepository;
import br.com.sigvet.sigvetapi.feature.veterinarian.VeterinarianRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateVaccinationUseCase implements CreateUseCase<VaccinationEntity> {

    private final VaccinationRepository repository;

    private final VaccineRepository vaccineRepository;
    private final AnimalRepository animalRepository;
    private final VeterinarianRepository veterinarianRepository;

    @Transactional
    @Override
    public VaccinationEntity execute(VaccinationEntity source) {
        final var animalId = source.getAnimal().getId();

        if (!animalRepository.existsById(animalId)) {
            throw new ApplicationException("Animal with id %d not found".formatted(animalId));
        }

        final var veterinarianId = source.getVeterinarian().getId();

        if (!veterinarianRepository.existsById(veterinarianId)) {
            throw new ApplicationException("Veterinarian with id %d not found".formatted(veterinarianId));
        }

        final var vaccineId = source.getVaccine().getId();

        if (!vaccineRepository.existsById(vaccineId)) {
            throw new ApplicationException("Vaccine with id %d not found".formatted(vaccineId));
        }

        // Caso não haja a vacina escolhida em estoque será lançada uma mensagem informativa
        final var vaccine = vaccineRepository.findById(vaccineId);

        if (vaccine.get().getStock() <= 0) {
            throw new ApplicationException("Vaccine with id %d out of stock".formatted(vaccineId));
        }

        return repository.save(Objects.requireNonNull(source));
    }

}
