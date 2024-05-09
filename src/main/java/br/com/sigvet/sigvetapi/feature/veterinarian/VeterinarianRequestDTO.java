package br.com.sigvet.sigvetapi.feature.veterinarian;

import br.com.sigvet.sigvetapi.feature.user.UserRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Schema(name = "Veterinário", example = "{\"name\":\"John Doe\",\"username\":\"johndoe\",\"document\":\"123.456.789-00\",\"email\":\"johndoe@example.com\",\"phone\":\"1234567890\",\"password\":\"mypassword\",\"confirmationPassword\":\"mypassword\",\"address\":{\"street\":\"123 Test St\",\"neighborhood\":\"Testville\",\"zipCode\":\"98765-432\",\"number\":20,\"cityId\":2},\"specialty\":\"Example Specialty\",\"crmv\":\"AB123456\",\"crmvUf\":\"UF\"}")
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
