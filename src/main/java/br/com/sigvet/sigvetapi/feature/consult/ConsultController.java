package br.com.sigvet.sigvetapi.feature.consult;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigvet.sigvetapi.common.CrudController;
import br.com.sigvet.sigvetapi.common.entities.AddressEntity;
import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;
import br.com.sigvet.sigvetapi.common.entities.CityEntity;
import br.com.sigvet.sigvetapi.common.entities.ConsultEntity;


@RestController
@RequestMapping("/consults")
public class ConsultController extends CrudController<ConsultEntity, ConsultRequestDTO> {

    public ConsultController(ConsultFacade facade, ConsultMapper mapper) {
        super(facade, mapper);
        attributeFilters.putAll(new HashMap<>() {
            {
                put(AnimalEntity.ANIMAL_ENTITY_FILTER_KEY, List.of("client"));
                put(AddressEntity.ADDRESS_ENTITY_FILTER_KEY, List.of("user"));
                put(CityEntity.CITY_ENTITY_FILTER_KEY, List.of("state"));
            }
        });
    }

}
