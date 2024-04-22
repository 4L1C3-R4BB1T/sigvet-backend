package br.com.sigvet.sigvetapi.feature.veterinarian;

import br.com.sigvet.sigvetapi.common.UserRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Size(message = "Specialty max size is 255 characters", max = 255)
    private String specialty;

    @NotBlank(message = "CRMV can't be blank")
    @Size(message = "CRMV max size is 45 characters", max = 45)
    @Pattern(message = "CRMV should be valid", regexp = "^[a-zA-Z0-9]*$")
    private String crmv;

    @NotBlank(message = "CRMV UF can't be blank")
    @Size(message = "CRMV UF max and min size is 2 characters", min = 2, max = 2)
    private String crmvUf;
    
}
