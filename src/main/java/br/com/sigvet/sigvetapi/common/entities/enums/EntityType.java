package br.com.sigvet.sigvetapi.common.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EntityType {
    ANIMAL("Animal"),
    USER("User"),
    VACCINE("Vaccine");

    private final String value;
}
