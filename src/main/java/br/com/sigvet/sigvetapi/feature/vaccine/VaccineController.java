package br.com.sigvet.sigvetapi.feature.vaccine;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigvet.sigvetapi.common.CrudController;
import br.com.sigvet.sigvetapi.common.entities.VaccineEntity;

@RestController
@RequestMapping("/vaccines")
public class VaccineController extends CrudController<VaccineEntity, VaccineRequestDTO> {

    public VaccineController(VaccineFacade facade, VaccineMapper mapper) {
        super(facade, mapper);
        attributeFilters.putAll(new HashMap<>() {
            {
                put(VaccineEntity.VACCINE_ENTITY_FILTER_KEY, List.of());
            }
        });
    }

}
