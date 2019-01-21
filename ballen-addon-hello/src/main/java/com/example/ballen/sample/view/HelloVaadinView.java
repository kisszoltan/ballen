package com.example.ballen.sample.view;

import com.example.ballen.view.DefaultLayout;
import com.example.ballen.view.MenuAction;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.Route;

@Route(value = "hello", layout = DefaultLayout.class)
@MenuAction(icon = VaadinIcon.GLOBE, weight = 50)
public class HelloVaadinView extends VerticalLayout implements HasDynamicTitle {

    public HelloVaadinView() {
        final Div div = new Div();
        div.setText(getTranslation("hello.message"));
        div.setTitle(getTranslation("hello.tooltip"));

        final Dialog dialog = new Dialog(div);
        dialog.setWidth("400px");
        dialog.setHeight("150px");

        add(new Button(getTranslation("open"), event -> dialog.open()));
    }

    @Override
    public String getPageTitle() {
        return getTranslation("hello.pageTitle");
    }
}