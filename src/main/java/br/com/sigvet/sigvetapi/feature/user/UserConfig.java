package br.com.sigvet.sigvetapi.feature.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    
    @Bean
    UserMapper userMapper() {
        return UserMapper.INSTANCE;
    }
}
