package br.com.sigvet.sigvetapi.feature.veterinarian;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigvet.sigvetapi.common.CrudController;
import br.com.sigvet.sigvetapi.common.entities.AddressEntity;
import br.com.sigvet.sigvetapi.common.entities.VeterinarianEntity;

@RestController
@RequestMapping("/veterinarians")
public class VeterinarianController extends CrudController<VeterinarianEntity, VeterinarianRequestDTO> {

    public VeterinarianController(VeterinarianFacade facade, VeterinarianMapper mapper) {
        super(facade, mapper);
        attributeFilters.putAll(new HashMap<>() {
            {   
                put(AddressEntity.ADDRESS_ENTITY_FILTER_KEY, List.of("user"));
            }
        });
    }

}
