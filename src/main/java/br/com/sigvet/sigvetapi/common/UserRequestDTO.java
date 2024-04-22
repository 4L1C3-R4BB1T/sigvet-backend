package br.com.sigvet.sigvetapi.common;

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
        private String street;
        private String neighborhood;
        private String zipCode;
        private Integer number;
        private Long cityId;
    }

}