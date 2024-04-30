package br.com.sigvet.sigvetapi.feature.diagnostic;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(name = "Diagnostico", example = "{\"diagnosis\":\"Example diagnosis\",\"comments\":\"Example comments\",\"consultId\":1}")
public record DiagnosticRequestDTO(

    @NotBlank(message = "Diagnosis can't be blank")
    @Size(message = "Diagnosis max size is 255 characters", max = 255)
    String diagnosis,

    String comments,
    
    @NotNull(message = "Consult ID can't be null") 
    Long consultId

) {}
