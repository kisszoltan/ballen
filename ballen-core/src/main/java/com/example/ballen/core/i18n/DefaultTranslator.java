package com.example.ballen.core.i18n;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.example.ballen.core.plugin.PluginRegistry;
import com.vaadin.flow.i18n.I18NProvider;

import lombok.extern.slf4j.Slf4j;

/**
 * {@link I18NProvider} implementation which tries to translate the provided
 * message key.
 * <p>
 * This implementation looks for the messages file in the plugin's namespace
 * directory or in 'i18n' directory (relative to the classpath root).
 * <p>
 * In case either the directory, the file or the message key was missing, the
 * original message key will be returned surrounded with exclamation marks like
 * for example <code>!messagekey!</code>.
 */
@Slf4j
@Component
public final class DefaultTranslator implements I18NProvider {

    private static final String MISSING_KEY = "!{0}!";
    private static final String DEFAULTS = "defaults";

    @Override
    public String getTranslation(final String message, final Locale locale, final Object... args) {
        return getTranslation(message, "messages", locale, args);
    }

    public String getTranslation(final String message, final String bundle, final Locale locale, final Object... args) {
        try {
            log.trace("Using {} locale for fetching text translations for {} key.", locale, message);
            return MessageFormat.format(getString(bundle, message, locale), args);
        } catch (final MissingResourceException e) {
            if (!DEFAULTS.equals(bundle)) {
                return getTranslation(message, DEFAULTS, locale, args);
            }
            log.error(e.getMessage());
            log.trace("Failed to translate '" + message + "' key.", e);
            // TODO fall back to default locale (maybe the first of provided locale list?)
            return MessageFormat.format(MISSING_KEY, message);
        }
    }

    private String getString(final String bundleName, final String message, final Locale locale) {
        for (String location : PluginRegistry.getPluginNamespaces()) {
            try {
                return ResourceBundle.getBundle(location + "." + bundleName, locale).getString(message);
            } catch (MissingResourceException e) {
                log.trace("Resource bundle '{}' was not found in {} package for {} locale.", bundleName, location,
                        locale);
            }
        }
        return ResourceBundle.getBundle("i18n." + bundleName, locale).getString(message);
    }

    @Override
    public List<Locale> getProvidedLocales() {
        // TODO read this list from application properties
        return Collections.unmodifiableList(Arrays.asList(Locale.ENGLISH, new Locale("hu")));
    }

}
