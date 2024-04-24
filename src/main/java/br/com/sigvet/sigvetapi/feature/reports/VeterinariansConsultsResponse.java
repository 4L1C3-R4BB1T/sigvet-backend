package br.com.sigvet.sigvetapi.feature.reports;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VeterinariansConsultsResponse {
    List<VeterinarianConsults> veterinarianConsults;
    LocalDateTime initialDate;
    LocalDateTime finalDate;
}
