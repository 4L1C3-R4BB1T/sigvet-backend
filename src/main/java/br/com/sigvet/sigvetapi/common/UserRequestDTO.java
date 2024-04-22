package br.com.sigvet.sigvetapi.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
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
    public static class Address {
        private String street;
        private String neighborhood;
        private String zipCode;
        private Integer number;
        private String city;
        private String state;
    }

}