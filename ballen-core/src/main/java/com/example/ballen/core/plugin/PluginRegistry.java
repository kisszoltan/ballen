package com.example.ballen.core.plugin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PluginRegistry {

    public PluginRegistry() {
        for (PluginDescriptor plugin : getPluginDescriptions()) {
            plugin.onLoad();
            log.trace("Plugin '{}' has been loaded with namespace '{}'.", plugin.getClass().getName(),
                    plugin.getNamespace());
        }
    }

    public static List<PluginDescriptor> getPluginDescriptions() {
        return SpringFactoriesLoader.loadFactories(PluginDescriptor.class, PluginRegistry.class.getClassLoader());
    }

    public static List<Class<?>> getPluginTypes() {
        return getPluginDescriptions().stream().map(p -> p.getClass()).collect(Collectors.toList());
    }

    public static List<String> getPluginNamespaces() {
        return getPluginDescriptions().stream().map(p -> p.getNamespace()).collect(Collectors.toList());
    }
}
