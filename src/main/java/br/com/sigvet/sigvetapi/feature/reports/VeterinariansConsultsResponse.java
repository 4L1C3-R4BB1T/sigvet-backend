package br.com.sigvet.sigvetapi.feature.reports;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    @JsonFormat(pattern = "YYYY-MM-dd")
    LocalDate initialDate;
    @JsonFormat(pattern = "YYYY-MM-dd")
    LocalDate finalDate;

}
