package com.example.ballen.auth;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.ballen.core.plugin.PluginDescriptor;

/**
 * Plugin to provide authentication functionalities.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class AuthenticationPlugin implements PluginDescriptor {

}
