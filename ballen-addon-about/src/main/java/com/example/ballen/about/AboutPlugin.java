package com.example.ballen.about;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.ballen.core.plugin.PluginDescriptor;

/**
 * Plugin to provide a view for describing the product, like listing available
 * plugins or show framework version.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class AboutPlugin implements PluginDescriptor {

}
