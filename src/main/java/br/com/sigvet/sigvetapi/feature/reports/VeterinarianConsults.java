package br.com.sigvet.sigvetapi.feature.reports;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VeterinarianConsults {
    String name;
    String crmv;
    String crmvUf;
    Long consults;
}
