package com.example.ballen.about.view;

import com.example.ballen.view.DefaultLayout;
import com.example.ballen.view.MenuAction;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.Version;

@Route(value = "about", layout = DefaultLayout.class)
@MenuAction(icon = VaadinIcon.INFO_CIRCLE)
public class AboutView extends HorizontalLayout implements HasDynamicTitle {

    public AboutView() {
        add(VaadinIcon.INFO_CIRCLE.create());
        add(new Span(getTranslation("about.message", Version.getFullVersion())));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
    }

    @Override
    public String getPageTitle() {
        return getTranslation("about.pageTitle");
    }
}
