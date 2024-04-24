package br.com.sigvet.sigvetapi.feature.diagnostic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DiagnosticRequestDTO(

    @NotBlank(message = "Diagnosis can't be blank")
    @Size(message = "Diagnosis max size is 255 characters", max = 255)
    String diagnosis,

    String comments,
    
    @NotNull(message = "Consult ID can't be null") 
    Long consultId

) {}
