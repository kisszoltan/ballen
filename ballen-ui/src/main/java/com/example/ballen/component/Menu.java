package com.example.ballen.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.example.ballen.ApplicationConfiguration;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinServletService;
import com.vaadin.flow.server.VaadinSession;

@org.springframework.stereotype.Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Menu extends FlexLayout {

    private static final long serialVersionUID = 2019_01_23_001L;
    private static final String SHOW_TABS = "show-tabs";

    private final HorizontalLayout header = new HorizontalLayout();
    private final Tabs tabs = new Tabs();

    @Autowired
    private transient ApplicationConfiguration config;

    public Menu() {
        setClassName("menu-bar");

        add(showMenuButton());

        // Initialise header component but populate it only in post-construct
        header.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        header.setClassName("menu-header");
        add(header);

        // container for the navigation buttons, which are added by addView()
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        setFlexGrow(1, tabs);
        add(tabs);

        // logout menu item
        add(logoutButton());
    }

    /**
     * @return a button for toggling the menu visibility on small screens
     */
    private Component showMenuButton() {
        final Button showMenu = new Button(getTranslation("menu.text"), event -> {
            if (tabs.getClassNames().contains(SHOW_TABS)) {
                tabs.removeClassName(SHOW_TABS);
            } else {
                tabs.addClassName(SHOW_TABS);
            }
        });
        showMenu.setClassName("menu-button");
        showMenu.getElement().getThemeList().add("small");
        showMenu.setIcon(new Icon(VaadinIcon.MENU));

        return showMenu;
    }

    /**
     * @return a logout button instance
     */
    private Component logoutButton() {
        final Button logoutButton = new Button(getTranslation("menu.logout"), VaadinIcon.SIGN_OUT.create());
        logoutButton.addClickListener(event -> {
            VaadinSession.getCurrent().getSession().invalidate();
            UI.getCurrent().getPage().reload();
        });

        logoutButton.getElement().getThemeList().add("tertiary-inline");

        return logoutButton;
    }

    // only finish header after the bean instantiation is finished so that we can
    // access application properties
    @PostConstruct
    private void postConstruct() {
        // Note! Image resource url is resolved here as it is dependent on the
        // execution mode (development or production) and browser ES level support
        String resolvedImage = VaadinServletService.getCurrent()
                .resolveResource(config.getLogo(), VaadinSession.getCurrent().getBrowser());

        Image image = new Image(resolvedImage, "");
        header.add(image);
        header.add(new Span(config.getName()));
    }

    /**
     * Add a view to the navigation menu
     *
     * @param viewClass that has a {@code Route} annotation
     * @param caption   view caption in the menu
     * @param icon      view icon in the menu
     */
    public void addView(Class<? extends Component> viewClass, String caption, Icon icon) {
        Tab tab = new Tab();
        RouterLink routerLink = new RouterLink(null, viewClass);
        routerLink.setClassName("menu-link");
        routerLink.add(icon);
        routerLink.add(new Span(caption));
        tab.add(routerLink);
        tabs.add(tab);
    }
}
