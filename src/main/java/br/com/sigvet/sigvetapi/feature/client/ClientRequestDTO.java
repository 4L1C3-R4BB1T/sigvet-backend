package br.com.sigvet.sigvetapi.feature.client;

public record ClientRequestDTO(
        String name,
        String username,
        String document,
        String email,
        String phone,
        String password,
        String confirmationPassword,
        Address address
) {

    public record Address(
            String street,
            String neighborhood,
            String zipCode,
            Integer number,
            String city,
            String state
    ) {}
}
