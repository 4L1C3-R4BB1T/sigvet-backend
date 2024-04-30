package br.com.sigvet.sigvetapi.feature.client;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigvet.sigvetapi.common.CrudController;
import br.com.sigvet.sigvetapi.common.entities.AddressEntity;
import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Clientes", description = "Agrupa endpoints para gerenciar clientes")
@RestController
@RequestMapping("/clients")
public class ClientController extends CrudController<ClientEntity, ClientRequestDTO> {
  
    public ClientController(ClientFacade clientFacade, ClientMapper clientMapper) {

        super(clientFacade, clientMapper);

        attributeFilters.putAll(new HashMap<>() {
            {   
                put(ClientEntity.CLIENT_ENTITY_FILTER_KEY, List.of("password"));
                put(AddressEntity.ADDRESS_ENTITY_FILTER_KEY, List.of("user"));
                put(AnimalEntity.ANIMAL_ENTITY_FILTER_KEY, List.of("client"));
            }
        });
    }

}
