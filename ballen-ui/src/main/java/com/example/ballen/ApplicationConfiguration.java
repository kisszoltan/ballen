package com.example.ballen;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ballen.application", ignoreUnknownFields = false)
public class ApplicationConfiguration {

    private String name;

    private String logo = "frontend://img/logo.png";

    public void setName(final String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(final String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }
}