package br.com.sigvet.sigvetapi.feature.client;

import br.com.sigvet.sigvetapi.feature.user.UserRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Schema(name = "Cliente", example = "{\"name\":\"John Doe\",\"username\":\"johndoe\",\"document\":\"123.456.789-00\",\"email\":\"johndoe@example.com\",\"phone\":\"1234567890\",\"password\":\"mypassword\",\"confirmationPassword\":\"mypassword\",\"address\":{\"street\":\"123 Test St\",\"neighborhood\":\"Testville\",\"zipCode\":\"98765-432\",\"number\":20,\"cityId\":2}}")
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
public class ClientRequestDTO extends UserRequestDTO {}
