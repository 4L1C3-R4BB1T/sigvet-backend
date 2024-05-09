package br.com.sigvet.sigvetapi.feature.vaccine;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigvet.sigvetapi.common.CrudController;
import br.com.sigvet.sigvetapi.common.entities.VaccineEntity;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Vacinas", description = "Agrupa endpoints para gerenciar vacinas")
@RestController
@RequestMapping("/api/v1/vaccines")
public class VaccineController extends CrudController<VaccineEntity, VaccineRequestDTO> {

    public VaccineController(VaccineFacade facade, VaccineMapper mapper) {
        super(facade, mapper);
    }

}
