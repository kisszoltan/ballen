package com.example.ballen.login.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ballen.core.auth.AccessControl;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.Route;

/**
 * UI content when the user is not logged in yet.
 */
@Route("login")
@StyleSheet("css/login-styles.css")
public class LoginScreen extends FlexLayout implements HasDynamicTitle {

    private TextField username;
    private PasswordField password;
    private Button login;
    private Button forgotPassword;
    @Autowired
    private AccessControl accessControl;

    public LoginScreen() {
        buildUI();
        username.focus();
    }

    private void buildUI() {
        setSizeFull();
        setClassName("login-screen");

        // login form, centered in the available part of the screen
        // layout to center login form when there is sufficient screen space
        FlexLayout centeringLayout = new FlexLayout();
        centeringLayout.setSizeFull();
        centeringLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        centeringLayout.setAlignItems(Alignment.CENTER);
        centeringLayout.add(buildLoginForm());

        // information text about logging in
        Component loginInformation = buildLoginInformation();

        add(loginInformation);
        add(centeringLayout);
    }

    private Component buildLoginForm() {
        FormLayout loginForm = new FormLayout();

        loginForm.setWidth("310px");
        loginForm.addFormItem(username = new TextField(), getTranslation("login.username.text"));
        username.setWidth("15em");
        username.setValue("admin");
        loginForm.add(new Html("<br/>"));
        loginForm.addFormItem(password = new PasswordField(), getTranslation("login.password.text"));
        password.setWidth("15em");

        HorizontalLayout buttons = new HorizontalLayout();
        loginForm.add(new Html("<br/>"));
        loginForm.add(buttons);

        buttons.add(login = new Button(getTranslation("login.button.text")));
        login.addClickListener(event -> login());
        loginForm.getElement().addEventListener("keypress", event -> login()).setFilter("event.key == 'Enter'");
        login.getElement().getThemeList().add("success primary");

        buttons.add(forgotPassword = new Button(getTranslation("login.forgotpassword.text")));
        forgotPassword.addClickListener(event -> showNotification(new Notification(getTranslation("login.hint.text"))));
        forgotPassword.getElement().getThemeList().add("tertiary");

        return loginForm;
    }

    private Component buildLoginInformation() {
        VerticalLayout loginInformation = new VerticalLayout();
        loginInformation.setClassName("login-information");

        H1 loginInfoHeader = new H1(getTranslation("login.header.text"));
        Span loginInfoText = new Span(getTranslation("login.info.text"));
        loginInformation.add(loginInfoHeader);
        loginInformation.add(loginInfoText);

        return loginInformation;
    }

    private void login() {
        login.setEnabled(false);
        try {
            if (accessControl.signIn(username.getValue(), password.getValue())) {
                getUI().get().navigate("");
            } else {
                showNotification(new Notification(getTranslation("login.failed.text")));
                username.focus();
            }
        } finally {
            login.setEnabled(true);
        }
    }

    private void showNotification(Notification notification) {
        // keep the notification visible a little while after moving the
        // mouse, or until clicked
        notification.setDuration(2000);
        notification.open();
    }

    @Override
    public String getPageTitle() {
        return getTranslation("login.pageTitle");
    }
}
