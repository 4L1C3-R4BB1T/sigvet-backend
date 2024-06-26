package br.com.sigvet.sigvetapi.feature.animal;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigvet.sigvetapi.common.CrudController;
import br.com.sigvet.sigvetapi.common.entities.AddressEntity;
import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;
import br.com.sigvet.sigvetapi.feature.animal.usecases.FindAnimalsByClientId;
import br.com.sigvet.sigvetapi.feature.animal.usecases.SearchAnimalByNameUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Animais", description = "Agrupa endpoints para gerenciar animais")
@RestController
@RequestMapping("/api/v1/animals")
public class AnimalController extends CrudController<AnimalEntity, AnimalRequestDTO> {

    private final SearchAnimalByNameUseCase searchAnimalByNameUseCase;

    private final FindAnimalsByClientId findAnimalsByClientId;

    public AnimalController(AnimalFacade facade, AnimalMapper mapper, SearchAnimalByNameUseCase searchAnimalByNameUseCase, FindAnimalsByClientId findAnimalsByClientId) {
        super(facade, mapper);
        attributeFilters.putAll(new HashMap<>() {
            {   
                put(ClientEntity.CLIENT_ENTITY_FILTER_KEY, List.of("animals", "roles", "password", "updatedAt", "createdAt"));
                put(AddressEntity.ADDRESS_ENTITY_FILTER_KEY, List.of("user"));
            }
        });
        this.searchAnimalByNameUseCase = searchAnimalByNameUseCase;
        this.findAnimalsByClientId = findAnimalsByClientId;
    }

    @Operation(description = "Endpoint para pesquisar animais pelo nome")
    @GetMapping("/search")
    public ResponseEntity<MappingJacksonValue> get(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok(buildJacksonValue(searchAnimalByNameUseCase.execute(name)));
    }

    @Operation(description = "Endpoint para buscar animais pelo id do cliente")
    @GetMapping("client/{id}")
    public ResponseEntity<MappingJacksonValue> getByClient(@PathVariable("id") Long id) {
        return ResponseEntity.ok(buildJacksonValue(findAnimalsByClientId.execute(id)));
    }
}
