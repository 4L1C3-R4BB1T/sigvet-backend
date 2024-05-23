package br.com.sigvet.sigvetapi.feature.state.usecases;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.feature.state.StateRepository;
import br.com.sigvet.sigvetapi.feature.state.StateResponseDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class FindAllStatesUseCase {
    
    private final StateRepository stateRepository;

    public List<StateResponseDTO> execute() {
        return stateRepository.findAll().stream().map(StateResponseDTO::from).toList();
    }
}
