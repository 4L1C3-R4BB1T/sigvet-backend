package br.com.sigvet.sigvetapi.feature.consult;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigvet.sigvetapi.common.CrudController;
import br.com.sigvet.sigvetapi.common.entities.AddressEntity;
import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;
import br.com.sigvet.sigvetapi.common.entities.CityEntity;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;
import br.com.sigvet.sigvetapi.common.entities.ConsultEntity;
import br.com.sigvet.sigvetapi.common.entities.VeterinarianEntity;
import br.com.sigvet.sigvetapi.feature.consult.usecases.SearchConsultByTermUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Tag(name = "Consultas", description = "Agrupa endpoints para gerenciar consultas")
@RestController
@RequestMapping("/api/v1/consults")
public class ConsultController extends CrudController<ConsultEntity, ConsultRequestDTO> {

    private final SearchConsultByTermUseCase searchConsultByTermUseCase;

    public ConsultController(ConsultFacade facade, ConsultMapper mapper, SearchConsultByTermUseCase searchConsultByTermUseCase) {
        super(facade, mapper);
        attributeFilters.putAll(new HashMap<>() {
            {
                put(AnimalEntity.ANIMAL_ENTITY_FILTER_KEY, List.of("createdAt", "updatedAt"));
                put(ClientEntity.CLIENT_ENTITY_FILTER_KEY, List.of("address","createdAt", "roles", "updatedAt", "animals", "password"));
                put(AddressEntity.ADDRESS_ENTITY_FILTER_KEY, List.of("user"));
                put(CityEntity.CITY_ENTITY_FILTER_KEY, List.of("state"));
                put(VeterinarianEntity.VETERINARIAN_ENTITY_FILTER_KEY, List.of("createdAt", "roles","updatedAt", "password", "address"));
            }
        });
        this.searchConsultByTermUseCase = searchConsultByTermUseCase;
    }

    @Operation(description = "Permite pesquisar consultas por termo")
    @GetMapping("/search")
    public ResponseEntity<MappingJacksonValue> search(@RequestParam("term") String term) {
        return ResponseEntity.ok(buildJacksonValue(searchConsultByTermUseCase.execute(term)));
    }
    

}
