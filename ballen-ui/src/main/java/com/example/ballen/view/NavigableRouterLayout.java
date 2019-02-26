package com.example.ballen.view;

import java.util.stream.Stream;

import com.github.appreciated.app.layout.component.appmenu.left.LeftNavigationComponent;
import com.github.appreciated.app.layout.router.AppLayoutRouterLayout;
import com.vaadin.flow.router.RouteData;
import com.vaadin.flow.server.VaadinService;

/**
 * Supper class for RouterLayout implementation those require the list of
 * routing targets with {@link MenuAction} annotation.
 * <p>
 * Usage on view classes:
 * 
 * <pre>
 * public class StandardLayout extends NavigableRouterLayout {
 *    ...
 * }
 * 
 * &#64;Route(value = "home", layout = StandardLayout.class)
 * public class HomeView extends VerticalLayout {
 *    ...
 * }
 * </pre>
 */
public abstract class NavigableRouterLayout extends AppLayoutRouterLayout {

    private static final String MESSAGE_KEY_PREFIX = "routes.";

    /**
     * @return a stream of routing data from router for Vaadin view classes which
     *         are marked with {@link MenuAction} annotation
     */
    public Stream<RouteData> listRoutes() {
        return VaadinService.getCurrent().getRouter().getRoutes().stream().filter(r -> {
            // Only views with MenuAction annotation may remain in the stream
            return r.getNavigationTarget().isAnnotationPresent(MenuAction.class);
        });
    }

    /**
     * @return a sorted routing data stream based on their weight property
     */
    public Stream<RouteData> listSortedRoutes() {
        // Order the stream by weight property of the actions
        return listRoutes().sorted((a, b) -> getAction(a).weight() - getAction(b).weight());
    }

    /**
     * @return a stream of {@link LeftNavigationComponent} instances for every
     *         routing target which has {@link MenuAction} annotation
     */
    public Stream<LeftNavigationComponent> listNavigationComponents() {
        return listSortedRoutes().map(r -> {
            final String text = getTranslation(MESSAGE_KEY_PREFIX + r.getUrl());
            return new LeftNavigationComponent(text, getAction(r).icon(), r.getNavigationTarget());
        });
    }

    /**
     * @param route route descriptor
     * @return menu action metadata for the view described by the given route
     */
    private MenuAction getAction(final RouteData route) {
        return route.getNavigationTarget().getAnnotation(MenuAction.class);
    }
}
