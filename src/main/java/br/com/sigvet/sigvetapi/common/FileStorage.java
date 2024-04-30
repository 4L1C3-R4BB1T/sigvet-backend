package br.com.sigvet.sigvetapi.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "file")
public class FileStorage {

    private String uploadDir;
    
}
