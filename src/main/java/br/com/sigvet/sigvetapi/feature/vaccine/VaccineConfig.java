package br.com.sigvet.sigvetapi.feature.vaccine;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VaccineConfig {

    @Bean
    VaccineMapper vaccineMapper() {
        return VaccineMapper.INSTANCE;
    }

}
