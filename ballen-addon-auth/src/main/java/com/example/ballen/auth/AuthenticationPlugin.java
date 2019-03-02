package com.example.ballen.auth;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.ballen.core.plugin.PluginDescriptor;

/**
 * Plugin to provide authentication functionalities.
 */
@Configuration
@ComponentScan
public class AuthenticationPlugin implements PluginDescriptor {

}
