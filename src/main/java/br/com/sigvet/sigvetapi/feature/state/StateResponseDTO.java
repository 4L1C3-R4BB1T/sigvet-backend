package br.com.sigvet.sigvetapi.feature.state;

import br.com.sigvet.sigvetapi.common.entities.StateEntity;

public record StateResponseDTO(
    String shortname,
    String name
) {

    public static StateResponseDTO from(final StateEntity source) {
        return new StateResponseDTO(source.getId(), source.getName());
    }
}
