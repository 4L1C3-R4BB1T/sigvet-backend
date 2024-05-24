package br.com.sigvet.sigvetapi.feature.state;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigvet.sigvetapi.feature.state.usecases.FindAllStatesUseCase;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/states")
public class StateController {

    private final FindAllStatesUseCase findAllStatesUseCase;
    
    @Operation(description = "Obtém todos os estados")
    @GetMapping
    public ResponseEntity<List<StateResponseDTO>> get() {
        return ResponseEntity.ok(findAllStatesUseCase.execute());
    }
}
