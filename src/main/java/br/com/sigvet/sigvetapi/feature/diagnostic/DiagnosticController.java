package br.com.sigvet.sigvetapi.feature.diagnostic;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigvet.sigvetapi.common.CrudController;
import br.com.sigvet.sigvetapi.common.entities.ConsultEntity;
import br.com.sigvet.sigvetapi.common.entities.DiagnosticEntity;
import br.com.sigvet.sigvetapi.common.entities.VeterinarianEntity;
import br.com.sigvet.sigvetapi.feature.diagnostic.usecases.SearchDiagnosticByTermUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Tag(name = "Diagnósticos", description = "Agrupa endpoints para gerenciar diagnósticos")
@RestController
@RequestMapping("/api/v1/diagnostics")
public class DiagnosticController extends CrudController<DiagnosticEntity, DiagnosticRequestDTO> {

    private final SearchDiagnosticByTermUseCase searchDiagnosticByTermUseCase;

    public DiagnosticController(DiagnosticFacade facade, DiagnosticMapper mapper, SearchDiagnosticByTermUseCase searchDiagnosticByTermUseCase) {
        super(facade, mapper);
        attributeFilters.putAll(new HashMap<>() {
            {
                put(ConsultEntity.CONSULT_ENTITY_FILTER_KEY, List.of("animal"));
                put(VeterinarianEntity.VETERINARIAN_ENTITY_FILTER_KEY, List.of("address", "password", "roles", "createdAt", "updatedAt"));
            }
        });
        this.searchDiagnosticByTermUseCase = searchDiagnosticByTermUseCase;
    }

    @Operation(description = "Permite pesquisar diagnosticos por um termo")
    @GetMapping("/search")
    public ResponseEntity<MappingJacksonValue> getMethodName(@RequestParam("term") String term) {
        return ResponseEntity.ok(buildJacksonValue(searchDiagnosticByTermUseCase.execute(term)));
    }
    

}
