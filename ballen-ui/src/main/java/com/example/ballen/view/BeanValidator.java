package com.example.ballen.view;

import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.MessageInterpolator;
import javax.validation.Validation;

import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.binder.ValueContext;

public class BeanValidator<T> implements Validator<T> {

    private final Configuration<?> validationConfig = Validation.byDefaultProvider().configure();
    private final javax.validation.Validator validator = validationConfig
            .messageInterpolator(new DefaultLocaleInterpolator()).buildValidatorFactory().getValidator();
    private final Locale defaultLocale;

    public BeanValidator(final Locale locale) {
        defaultLocale = locale;
    }

    @Override
    public ValidationResult apply(T value, ValueContext context) {
        final Set<ConstraintViolation<T>> validationResult = validator.validate(value);
        if (validationResult.isEmpty()) {
            return ValidationResult.ok();
        } else {
            final String errorText = validationResult.stream().map(ConstraintViolation::getMessage).distinct()
                    .collect(Collectors.joining(", "));
            return ValidationResult.error(errorText);
        }
    }

    /**
     * Built in default message interpolator uses {@link Locale#getDefault()} which in case of Vaadin could defer from
     * current component's locale, therefore we provide the default locale for the interpolator during component(/form)
     * initialization.
     */
    private class DefaultLocaleInterpolator implements MessageInterpolator {

        @Override
        public String interpolate(String messageTemplate, Context context) {
            return interpolate(messageTemplate, context, defaultLocale);
        }

        @Override
        public String interpolate(String messageTemplate, Context context, Locale locale) {
            return validationConfig.getDefaultMessageInterpolator().interpolate(messageTemplate, context, locale);
        }
    }
}
