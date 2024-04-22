package br.com.sigvet.sigvetapi.feature.veterinarian;

import br.com.sigvet.sigvetapi.common.UserRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Specialty can't be blank")
    @Size(max = 255)
    private String specialty;

    @NotBlank(message = "CRMV can't be blank")
    @Size(max = 45)
    private String crmv;

    @NotBlank(message = "CRMV UF can't be blank")
    @Size(max = 2)
    private String crmvUf;
    
}
