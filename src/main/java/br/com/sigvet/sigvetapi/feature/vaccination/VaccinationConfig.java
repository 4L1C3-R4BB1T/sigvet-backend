package br.com.sigvet.sigvetapi.feature.vaccination;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VaccinationConfig {

    @Bean
    VaccinationMapper vaccinationMapper() {
        return VaccinationMapper.INSTANCE;
    }

}
