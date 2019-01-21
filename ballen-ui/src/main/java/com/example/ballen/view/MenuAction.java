package com.example.ballen.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.vaadin.flow.component.icon.VaadinIcon;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MenuAction {

        int weight() default Integer.MAX_VALUE;

        /**
         * Codepoint for menu item icon resource. See VaadinIcons for exact values.
         *
         * @see VaadinIcons
         */
        VaadinIcon icon() default VaadinIcon.VAADIN_H; 
}
