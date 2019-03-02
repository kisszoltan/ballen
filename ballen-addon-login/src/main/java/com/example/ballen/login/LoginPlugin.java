package com.example.ballen.login;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.ballen.core.plugin.PluginDescriptor;

/**
 * Plugin to provide login functionalities.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class LoginPlugin implements PluginDescriptor {

}
