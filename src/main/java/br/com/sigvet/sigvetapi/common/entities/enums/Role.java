package br.com.sigvet.sigvetapi.common.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    ADMIN("ADMIN"),
    CLIENT("CLIENT");

    private final String value;
}
