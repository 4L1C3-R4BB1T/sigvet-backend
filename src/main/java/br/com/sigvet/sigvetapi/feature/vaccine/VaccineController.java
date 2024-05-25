package br.com.sigvet.sigvetapi.feature.vaccine;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigvet.sigvetapi.common.CrudController;
import br.com.sigvet.sigvetapi.common.entities.VaccineEntity;
import br.com.sigvet.sigvetapi.feature.vaccine.usecases.SearchVaccineByNameUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Vacinas", description = "Agrupa endpoints para gerenciar vacinas")
@RestController
@RequestMapping("/api/v1/vaccines")
public class VaccineController extends CrudController<VaccineEntity, VaccineRequestDTO> {

    private final SearchVaccineByNameUseCase searchVaccineByNameUseCase;

    public VaccineController(VaccineFacade facade, VaccineMapper mapper, SearchVaccineByNameUseCase searchVaccineByNameUseCase) {
        super(facade, mapper);
        this.searchVaccineByNameUseCase = searchVaccineByNameUseCase;
    }

    @Operation(description = "Endpoint para pesquisar vacinas por nome")
    @GetMapping("/search")
    public ResponseEntity<MappingJacksonValue> searchByName(@RequestParam(name = "name") String name) {
        final var newAttributeFilters = new HashMap<>(attributeFilters);
        newAttributeFilters.put(VaccineEntity.VACCINE_ENTITY_FILTER_KEY, List.of());
        return ResponseEntity.ok(buildJacksonValue(searchVaccineByNameUseCase.execute(name), newAttributeFilters));
    }

}
