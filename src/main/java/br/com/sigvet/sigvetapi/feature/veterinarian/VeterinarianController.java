package br.com.sigvet.sigvetapi.feature.veterinarian;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigvet.sigvetapi.common.CrudController;
import br.com.sigvet.sigvetapi.common.entities.VeterinarianEntity;

@RestController
@RequestMapping("/veterinarians")
public class VeterinarianController extends CrudController<VeterinarianEntity, VeterinarianRequestDTO> {

    public VeterinarianController(VeterinarianFacade facade, VeterinarianMapper mapper) {
        super(facade, mapper);
    }

    
}
