package com.example.ballen;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.example.ballen.core.plugin.PluginRegistry;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
public class DefaultServletInitializer extends SpringBootServletInitializer {

    /**
     * Main entry point for the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(getSources(), args);
    }

    @Override
    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
        return builder.sources(getSources());
    }

    /**
     * @return a list of source files to be added to Spring Boot as primarily loaded
     *         sources
     */
    private static Class<?>[] getSources() {
        final List<Class<?>> classList = PluginRegistry.getPluginTypes();
        classList.add(0, DefaultServletInitializer.class);

        return classList.toArray(new Class<?>[] {});
    }
}
