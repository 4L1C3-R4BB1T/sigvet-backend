package br.com.sigvet.sigvetapi.common.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EntityType {
    CLIENT("Client"),
    VETERINARIAN("Veterinarian"),
    VACCINE("Vaccine");

    private final String value;
}
