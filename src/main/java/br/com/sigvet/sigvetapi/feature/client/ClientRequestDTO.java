package br.com.sigvet.sigvetapi.feature.client;

import br.com.sigvet.sigvetapi.common.UserRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
public class ClientRequestDTO extends UserRequestDTO {}
