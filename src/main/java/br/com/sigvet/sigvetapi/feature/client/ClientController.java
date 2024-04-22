package br.com.sigvet.sigvetapi.feature.client;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigvet.sigvetapi.common.CrudController;
import br.com.sigvet.sigvetapi.common.entities.AddressEntity;
import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;
import br.com.sigvet.sigvetapi.common.entities.CityEntity;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;
import br.com.sigvet.sigvetapi.common.entities.StateEntity;

@RestController
@RequestMapping("/clients")
public class ClientController extends CrudController<ClientEntity, ClientRequestDTO> {
  
    public ClientController(ClientFacade clientFacade, ClientMapper clientMapper) {

        super(clientFacade, clientMapper);

        // As propriedades dentro da lista serve pra ocultar determinada propriedade da entididade
        attributeFilters.putAll(new HashMap<>() {
            {   
                put(ClientEntity.CLIENT_ENTITY_FILTER_KEY, List.of());
                put(AddressEntity.ADDRESS_ENTITY_FILTER_KEY, List.of("user"));
                put(CityEntity.CITY_ENTITY_FILTER_KEY, List.of());
                put(StateEntity.STATE_ENTITY_FILTER_KEY, List.of());
                put(AnimalEntity.ANIMAL_ENTITY_FILTER_KEY, List.of("client"));
            }
        });
    }

}
