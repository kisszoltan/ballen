package com.example.ballen.auth;

import org.springframework.context.annotation.Bean;

import com.example.ballen.core.auth.AccessControl;

public class MockConfiguration {

    @Bean
    public AccessControl mockAccessControl() {
        return new MockAccessControl();
    }
}
