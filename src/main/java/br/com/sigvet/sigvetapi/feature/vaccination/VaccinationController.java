package br.com.sigvet.sigvetapi.feature.vaccination;

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
import br.com.sigvet.sigvetapi.common.entities.CityEntity;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;
import br.com.sigvet.sigvetapi.common.entities.VaccinationEntity;
import br.com.sigvet.sigvetapi.common.entities.VaccineEntity;
import br.com.sigvet.sigvetapi.common.entities.VeterinarianEntity;
import br.com.sigvet.sigvetapi.feature.vaccination.usecases.FindVaccinationByClientIdUseCase;
import br.com.sigvet.sigvetapi.feature.vaccination.usecases.SearchVaccinationUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Vacinações", description = "Agrupa endpoints para gerenciar vacinações")
@RestController
@RequestMapping("/api/v1/vaccinations")
public class VaccinationController extends CrudController<VaccinationEntity, VaccinationRequestDTO> {

    private final SearchVaccinationUseCase searchVaccinationUseCase;

    private final FindVaccinationByClientIdUseCase findVaccinationByClientIdUseCase;

    public VaccinationController(VaccinationFacade facade, VaccinationMapper mapper, FindVaccinationByClientIdUseCase findVaccinationByClientIdUseCase, SearchVaccinationUseCase searchVaccinationUseCase) {
        super(facade, mapper);
        attributeFilters.putAll(new HashMap<>() {
            {
                put(AnimalEntity.ANIMAL_ENTITY_FILTER_KEY, List.of( "createdAt", "updatedAt"));
                put(AddressEntity.ADDRESS_ENTITY_FILTER_KEY, List.of("address"));
                put(ClientEntity.CLIENT_ENTITY_FILTER_KEY, List.of("address", "animals", "password", "roles", "createdAt", "updatedAt"));
                put(CityEntity.CITY_ENTITY_FILTER_KEY, List.of("state"));
                put(VaccineEntity.VACCINE_ENTITY_FILTER_KEY, List.of("createdAt", "updatedAt"));
                put(VeterinarianEntity.VETERINARIAN_ENTITY_FILTER_KEY, List.of("password", "address", "roles", "createdAt", "updatedAt"));
            }
        });
        this.searchVaccinationUseCase = searchVaccinationUseCase;
        this.findVaccinationByClientIdUseCase = findVaccinationByClientIdUseCase;
    }

    @Operation(description = "Permite pesquisar por vacinações")
    @GetMapping("/search")
    public ResponseEntity<MappingJacksonValue> search(@RequestParam("term") String term) {
        return ResponseEntity.ok(buildJacksonValue(searchVaccinationUseCase.execute(term)));
    }

    @Operation(description = "Permite buscar vacinações a partir do id do cliente")
    @GetMapping("/client/{id}")
    public ResponseEntity<MappingJacksonValue> search(@PathVariable Long id) {
        return ResponseEntity.ok(buildJacksonValue(this.findVaccinationByClientIdUseCase.execute(id)));
    }




}
