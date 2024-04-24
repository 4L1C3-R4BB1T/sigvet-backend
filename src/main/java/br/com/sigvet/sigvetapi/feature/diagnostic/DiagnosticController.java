package br.com.sigvet.sigvetapi.feature.diagnostic;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigvet.sigvetapi.common.CrudController;
import br.com.sigvet.sigvetapi.common.entities.ConsultEntity;
import br.com.sigvet.sigvetapi.common.entities.DiagnosticEntity;

@RestController
@RequestMapping("/diagnostics")
public class DiagnosticController extends CrudController<DiagnosticEntity, DiagnosticRequestDTO> {

    public DiagnosticController(DiagnosticFacade facade, DiagnosticMapper mapper) {
        super(facade, mapper);
        attributeFilters.putAll(new HashMap<>() {
            {
                put(DiagnosticEntity.DIAGNOSTIC_ENTITY_FILTER_KEY, List.of());
                put(ConsultEntity.CONSULT_ENTITY_FILTER_KEY, List.of("animal", "veterinarian"));
            }
        });
    }

}