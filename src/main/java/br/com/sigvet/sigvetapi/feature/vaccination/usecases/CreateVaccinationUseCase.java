package br.com.sigvet.sigvetapi.feature.vaccination.usecases;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
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


        if (Objects.nonNull(source.getHour()) && !(source.getHour().isAfter(LocalTime.of(8, 0)) && source.getHour().isBefore(LocalTime.of(19, 0)))) {
            throw new ApplicationException("O horário deve estar entre 8:00 horas e 18:59 horas");
        }
        
        if (source.getDateTime().isBefore(LocalDateTime.now())) {
            throw new ApplicationException("A data deve estar no presente ou futuro");
        }
    
        if (!animalRepository.existsById(animalId)) {
            throw new ApplicationException("Animal com id %d não encontrado".formatted(animalId));
        }

        final var veterinarianId = source.getVeterinarian().getId();

        if (!veterinarianRepository.existsById(veterinarianId)) {
            throw new ApplicationException("Veterinário com id %d não encontrado".formatted(veterinarianId));
        }

        final var vaccineId = source.getVaccine().getId();

        if (!vaccineRepository.existsById(vaccineId)) {
            throw new ApplicationException("Vacina com id %d não encontrado".formatted(vaccineId));
        }

        // Caso não haja a vacina escolhida em estoque será lançada uma mensagem informativa
        final var vaccine = vaccineRepository.findById(vaccineId).get();

        if (vaccine.getStock() <= 0) {
            throw new ApplicationException("Vacina com %d não tem estoque disponível".formatted(vaccineId));
        }

        vaccine.decreaseStock();

        return repository.save(Objects.requireNonNull(source));
    }

}
 