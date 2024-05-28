package br.com.sigvet.sigvetapi.feature.client;

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
import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;
import br.com.sigvet.sigvetapi.feature.client.usecases.SearchClientByNameUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Clientes", description = "Agrupa endpoints para gerenciar clientes")
@RestController
@RequestMapping("/api/v1/clients")
public class ClientController extends CrudController<ClientEntity, ClientRequestDTO> {

    private final SearchClientByNameUseCase searchClientByNameUseCase;
  
    public ClientController(ClientFacade clientFacade, ClientMapper clientMapper, SearchClientByNameUseCase searchClientByNameUseCase) {
        super(clientFacade, clientMapper);

        attributeFilters.putAll(new HashMap<>() {
            {   
                put(ClientEntity.CLIENT_ENTITY_FILTER_KEY, List.of("password"));
                put(AddressEntity.ADDRESS_ENTITY_FILTER_KEY, List.of("user"));
                put(AnimalEntity.ANIMAL_ENTITY_FILTER_KEY, List.of("client"));
            }
        });
        this.searchClientByNameUseCase = searchClientByNameUseCase;
    }

    @Operation(description = "Endpoint para pesquisar clientes por nome")
    @GetMapping("/search")
    public ResponseEntity<MappingJacksonValue> searchByName(@RequestParam(name = "name") String name) {
        final var newAttributeFilters = new HashMap<>(attributeFilters);
        newAttributeFilters.put(ClientEntity.CLIENT_ENTITY_FILTER_KEY, List.of("password", "address","roles"));
        return ResponseEntity.ok(buildJacksonValue(searchClientByNameUseCase.execute(name), newAttributeFilters));
    }

}
