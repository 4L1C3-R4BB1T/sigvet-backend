package br.com.sigvet.sigvetapi.feature.veterinarian.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.VeterinarianEntity;
import br.com.sigvet.sigvetapi.common.usecases.DeleteUseCase;
import br.com.sigvet.sigvetapi.feature.user.UserRepository;
import br.com.sigvet.sigvetapi.feature.vaccination.VaccinationRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteVeterinarianUseCase implements DeleteUseCase<VeterinarianEntity> {

    private final UserRepository userRepository;

     private final VaccinationRepository vaccinationRepository;

    @Transactional
    @Override
    public void execute(Long id) {
        if (userRepository.existsById(Objects.requireNonNull(id))) {
            vaccinationRepository.deleteByVeterinarianId(id);
            userRepository.deleteById(id);
            return;
        }
        throw new ApplicationException("Veterinário com id %d não encontrado".formatted(id));
    }
    
}
