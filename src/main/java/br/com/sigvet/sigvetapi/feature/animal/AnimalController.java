package br.com.sigvet.sigvetapi.feature.animal;

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
@RequestMapping("/animals")
public class AnimalController extends CrudController<AnimalEntity, AnimalRequestDTO> {

    public AnimalController(AnimalFacade facade, AnimalMapper mapper) {
        super(facade, mapper);
        attributeFilters.putAll(new HashMap<>() {
            {   
                put(ClientEntity.CLIENT_ENTITY_FILTER_KEY, List.of("animals", "address"));
                put(AnimalEntity.ANIMAL_ENTITY_FILTER_KEY, List.of());
                put(AddressEntity.ADDRESS_ENTITY_FILTER_KEY, List.of());
                put(CityEntity.CITY_ENTITY_FILTER_KEY, List.of());
                put(StateEntity.STATE_ENTITY_FILTER_KEY, List.of());
            }
        });
    }
}
