package br.com.sigvet.sigvetapi.feature.vaccination;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigvet.sigvetapi.common.CrudController;
import br.com.sigvet.sigvetapi.common.entities.AddressEntity;
import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;
import br.com.sigvet.sigvetapi.common.entities.CityEntity;
import br.com.sigvet.sigvetapi.common.entities.VaccinationEntity;
import br.com.sigvet.sigvetapi.common.entities.VaccineEntity;
import br.com.sigvet.sigvetapi.common.entities.VeterinarianEntity;

@RestController
@RequestMapping("/vaccinations")
public class VaccinationController extends CrudController<VaccinationEntity, VaccinationRequestDTO> {

    public VaccinationController(VaccinationFacade facade, VaccinationMapper mapper) {
        super(facade, mapper);
        attributeFilters.putAll(new HashMap<>() {
            {
                put(VaccinationEntity.VACCINATION_ENTITY_FILTER_KEY, List.of());
                put(VaccineEntity.VACCINE_ENTITY_FILTER_KEY, List.of());
                put(VeterinarianEntity.VETERINARIAN_ENTITY_FILTER_KEY, List.of());
                put(AnimalEntity.ANIMAL_ENTITY_FILTER_KEY, List.of("client"));
                put(AddressEntity.ADDRESS_ENTITY_FILTER_KEY, List.of("user"));
                put(CityEntity.CITY_ENTITY_FILTER_KEY, List.of("state"));
            }
        });
    }

}
