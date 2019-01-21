package com.example.ballen.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.example.ballen.auth.view.LoginScreen;
import com.vaadin.flow.component.Component;

@org.springframework.stereotype.Component
@ConfigurationProperties(prefix = "ballen.login", ignoreUnknownFields = true)
public class AuthenticationConfiguration {

    private Class<? extends Component> view = LoginScreen.class;

    public Class<? extends Component> getView() {
        return view;
    }

    public void setView(final Class<? extends Component> view) {
        this.view = view;
    }

}