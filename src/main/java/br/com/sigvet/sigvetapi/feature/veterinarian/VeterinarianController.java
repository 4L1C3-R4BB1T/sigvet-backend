package br.com.sigvet.sigvetapi.feature.veterinarian;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigvet.sigvetapi.common.CrudController;
import br.com.sigvet.sigvetapi.common.entities.AddressEntity;
import br.com.sigvet.sigvetapi.common.entities.VeterinarianEntity;
import br.com.sigvet.sigvetapi.feature.veterinarian.usecases.SearchVeterinarianByNameUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Veterinários", description = "Agrupa endpoints para gerenciar veterinários")
@RestController
@RequestMapping("/api/v1/veterinarians")
public class VeterinarianController extends CrudController<VeterinarianEntity, VeterinarianRequestDTO> {

    private final SearchVeterinarianByNameUseCase searchVeterinarianByNameUseCase;

    public VeterinarianController(VeterinarianFacade facade, VeterinarianMapper mapper, SearchVeterinarianByNameUseCase searchVeterinarianByNameUseCase) {
        super(facade, mapper);
        attributeFilters.putAll(new HashMap<>() {
            {   
                put(VeterinarianEntity.VETERINARIAN_ENTITY_FILTER_KEY, List.of("password"));
                put(AddressEntity.ADDRESS_ENTITY_FILTER_KEY, List.of("user"));
            }
        });
        this.searchVeterinarianByNameUseCase = searchVeterinarianByNameUseCase;
    }

    @Operation(description = "Pesquisar veterinários pelo nome")
    @GetMapping("/search")
    public ResponseEntity<MappingJacksonValue> search(@RequestParam("name") final String name) {
        final var newAttributeFilters = new HashMap<>(attributeFilters);
        newAttributeFilters.put(VeterinarianEntity.VETERINARIAN_ENTITY_FILTER_KEY, List.of("password", "address", "roles"));
        return ResponseEntity.ok(buildJacksonValue(searchVeterinarianByNameUseCase.execute(name), newAttributeFilters));
    } 

}
