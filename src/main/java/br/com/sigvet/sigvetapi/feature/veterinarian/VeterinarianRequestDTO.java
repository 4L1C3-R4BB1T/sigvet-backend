package br.com.sigvet.sigvetapi.feature.veterinarian;

import br.com.sigvet.sigvetapi.common.UserRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VeterinarianRequestDTO extends UserRequestDTO {

    private String specialty;

    private String crmv;

    private String crmvUf;
    
}
