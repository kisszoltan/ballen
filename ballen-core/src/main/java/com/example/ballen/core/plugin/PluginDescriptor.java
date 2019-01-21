package com.example.ballen.core.plugin;

/**
 * Interface to describe framework plugins.
 */
public interface PluginDescriptor {

    /**
     * @return by default the simple class name of the plugin, however certain plugin instances may override this
     */
    default String getName() {
        return getClass().getSimpleName();
    }

    /**
     * @return by default the package name of the plugin, however certain plugin instances may override this
     */
    default String getNamespace() {
        return getClass().getPackage().getName();
    }

    /**
     * Called when the plugin has been identified on the classpath.
     */
    default void onLoad() {
    }
}
