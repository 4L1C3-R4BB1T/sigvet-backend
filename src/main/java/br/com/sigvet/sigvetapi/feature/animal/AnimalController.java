package br.com.sigvet.sigvetapi.feature.animal;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigvet.sigvetapi.common.CrudController;
import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;

@RestController
@RequestMapping("/animals")
public class AnimalController extends CrudController<AnimalEntity, AnimalRequestDTO> {

    public AnimalController(AnimalFacade facade, AnimalMapper mapper) {
        super(facade, mapper);
        attributeFilters.putAll(new HashMap<>() {
            {   
                put(ClientEntity.CLIENT_ENTITY_FILTER_KEY, List.of("animals", "address"));
            }
        });
    }
}
