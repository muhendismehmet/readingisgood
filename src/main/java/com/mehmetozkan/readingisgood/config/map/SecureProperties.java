package com.mehmetozkan.readingisgood.config.map;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "secure")
public class SecureProperties {

    private String username;
    private String password;
    private String roles;
}
