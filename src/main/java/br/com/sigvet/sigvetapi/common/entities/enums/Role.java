package br.com.sigvet.sigvetapi.common.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    ADMIN("ADMIN"),
    UNKNOWN("UNKNOWN"),
    VET("VET"),
    CLIENT("CLIENT");

    private final String value;
}
 