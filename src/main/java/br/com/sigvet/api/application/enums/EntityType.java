package br.com.sigvet.api.application.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EntityType {
    USUARIO("usuario"),
    ANIMAL("animal"),
    VACINA("vacina");

    private final String value;
}
