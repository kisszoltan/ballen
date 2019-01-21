package com.example.ballen;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ballen.application", ignoreUnknownFields = false)
public class ApplicationConfiguration {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}