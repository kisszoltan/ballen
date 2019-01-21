package com.example.ballen.sample;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.ballen.core.plugin.PluginDescriptor;

/**
 * Hello World plugin.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class HelloWorldPlugin implements PluginDescriptor {

}
