package br.com.sigvet.sigvetapi.feature.user;

import br.com.sigvet.sigvetapi.common.validators.UserPasswordMatch;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@UserPasswordMatch 
public class UserRequestDTO {
    
    @NotBlank(message = "Name can't be blank")
    @Size(message = "Name max size is 100 characters", max = 100)
    protected String name;

    @NotBlank(message = "Username can't be blank")
    @Size(message = "Username max size is 100 characters", max = 100)
    protected String username;

    @NotBlank(message = "Document can't be blank")
    @Size(message = "Document max size is 14 characters", max = 14)
    @Pattern(message = "Document should be valid", regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$")
    protected String document;

    @NotBlank(message = "Email can't be blank")
    @Size(message = "Email max size is 100 characters", max = 100)
    @Email(message = "Email should be valid")
    protected String email;

    @NotBlank(message = "Phone can't be blank")
    @Size(message = "Phonw max size is 18 characters", max = 18)
    protected String phone;

    @Size(message = "Password max size is 100 characters", max = 100)
    protected String password;

    
    @Size(message = "Confirmation Password max size is 100 characters", max = 100)
    protected String confirmationPassword;

    @Valid
    protected Address address;

    @Schema(name = "Endereço")
    @Getter 
    @Setter 
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Address {

        @NotBlank(message = "Street can't be blank")
        @Size(message = "Street max size is 255 characters", max = 255)
        private String street;

        @NotBlank(message = "Neighborhood can't be blank")
        @Size(message = "Neighborhood max size is 255 characters", max = 255)
        private String neighborhood;

        @NotBlank(message = "Zip Code can't be blank")
        @Size(message = "Zip Code max size is 9 characters", max = 9)
        @Pattern(message = "Zip Code should be valid", regexp = "^\\d{5}-?\\d{3}$")
        private String zipCode;

        @NotNull(message = "Number can't be blank")
        @PositiveOrZero(message = "Number should be positive or zero")
        private Integer number;

        @NotNull(message = "City ID can't be null")
        private Long cityId;

    }

}