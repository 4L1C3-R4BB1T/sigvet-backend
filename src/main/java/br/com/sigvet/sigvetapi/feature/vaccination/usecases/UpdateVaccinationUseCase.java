package br.com.sigvet.sigvetapi.feature.vaccination.usecases;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.VaccinationEntity;
import br.com.sigvet.sigvetapi.common.usecases.UpdateUseCase;
import br.com.sigvet.sigvetapi.feature.vaccination.VaccinationMapper;
import br.com.sigvet.sigvetapi.feature.vaccination.VaccinationRepository;
import br.com.sigvet.sigvetapi.feature.vaccine.VaccineRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateVaccinationUseCase implements UpdateUseCase<VaccinationEntity> {

    private final VaccinationRepository repository;

    private final VaccineRepository vaccineRepository;

    private final VaccinationMapper vaccinationMapper;

    @Transactional
    @Override
    public void execute(Long id, VaccinationEntity source) {
        final var vaccinationOptional = repository.findById(Objects.requireNonNull(id));

        if (vaccinationOptional.isEmpty()) {
            throw new ApplicationException("Vacinação com id %d não encontrada".formatted(id));
        }

        final var vaccination = vaccinationOptional.get();
        
        if (Objects.nonNull(source.getHour()) && !(source.getHour().isAfter(LocalTime.of(7, 59)) && source.getHour().isBefore(LocalTime.of(19, 0)))) {
            throw new ApplicationException("O horário deve estar entre 8:00 horas e 18:59 horas");
        }

        final var vaccineId = source.getVaccine().getId();

        final var vaccine = vaccineRepository.findById(
            source.getVaccine().getId()).orElseThrow(
                () -> new ApplicationException("Vacina com id %d não encontrada".formatted(vaccineId)));
        
        if (vaccine.getExpirationDate().isBefore(LocalDate.now()) || vaccine.getExpirationDate().isEqual(LocalDate.now())) {
            throw new ApplicationException("A vacina está vencida");
        }

       
        if (vaccination.getVaccine().getId() != vaccineId) { // Se alterar a vacina eu aumento o stock dela em mais 1
            // Caso a vacina pra qual se deseja alterar não possua estoque disponível
            if (vaccine.getStock() <= 0) {
                throw new ApplicationException("Vacina com %d não tem estoque disponível".formatted(vaccineId));
            }
            vaccination.getVaccine().increaseStock();
            vaccine.decreaseStock();
        }

        vaccinationMapper.map(vaccination, source);
        vaccination.setVaccine(vaccine);
        repository.save(vaccination);
    }

}
