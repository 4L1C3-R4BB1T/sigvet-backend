package br.com.sigvet.sigvetapi.common;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class UserRequestDTO {
    
    protected String name;
    protected String username;
    protected String document;
    protected String email;
    protected String phone;
    protected String password;
    protected String confirmationPassword;
    protected Address address;

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
        @Size(message = "Zip Code max size is 8 characters", max = 8)
        private String zipCode;

        @NotNull(message = "Number can't be blank")
        @PositiveOrZero(message = "Number should be positive or zero")
        private Integer number;

        @NotNull(message = "City ID can't be null")
        private Long cityId;

    }

}