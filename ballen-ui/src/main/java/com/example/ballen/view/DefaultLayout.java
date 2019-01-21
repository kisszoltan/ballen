package com.example.ballen.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ballen.component.Menu;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.RouteData;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

/**
 * The layout of the pages e.g. About and Inventory.
 */
@StyleSheet("css/main-styles.css")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class DefaultLayout extends FlexLayout implements RouterLayout {

    private static final String MESSAGE_KEY_PREFIX = "routes.";

    public DefaultLayout(@Autowired final Menu menu) {
        setSizeFull();
        setClassName("main-layout");

        VaadinService.getCurrent().getRouter().getRoutes().stream().filter(r -> {
            // Only views with MenuAction annotation may remain in the stream
            return r.getNavigationTarget().isAnnotationPresent(MenuAction.class);
        }).sorted((a, b) -> {
            // Order the stream by weight property of the actions
            return getAction(a).weight() - getAction(b).weight();
        }).forEachOrdered(r -> {
            final MenuAction action = getAction(r);
            final String text = getTranslation(MESSAGE_KEY_PREFIX + r.getUrl());
            menu.addView(r.getNavigationTarget(), text, action.icon().create());
        });

        add(menu);
    }

    /**
     * @param route route descriptor
     * @return menu action metadata for the view described by the given route
     */
    private MenuAction getAction(final RouteData route) {
        return route.getNavigationTarget().getAnnotation(MenuAction.class);
    }

}
