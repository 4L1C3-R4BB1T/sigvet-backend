package br.com.sigvet.sigvetapi.feature.veterinarian;

import br.com.sigvet.sigvetapi.common.UserRequestDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class VeterinarianRequestDTO extends UserRequestDTO {

    private String specialty;

    private String crmv;

    private String crmvUf;
    
}
