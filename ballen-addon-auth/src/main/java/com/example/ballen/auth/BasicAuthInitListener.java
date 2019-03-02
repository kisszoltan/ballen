package com.example.ballen.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ballen.core.auth.AccessControl;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

/**
 * This class is used to listen to BeforeEnter event of all UIs in order to
 * check whether a user is signed in or not before allowing entering any page.
 * It is registered in a file named
 * com.vaadin.flow.server.VaadinServiceInitListener in META-INF/services.
 */
@Service
public class BasicAuthInitListener implements VaadinServiceInitListener {

    @Autowired
    private transient AuthenticationConfiguration config;

    @Autowired
    private transient AccessControl accessControl;

    @Override
    public void serviceInit(final ServiceInitEvent initEvent) {
        initEvent.getSource().addUIInitListener(uiInitEvent -> {
            uiInitEvent.getUI().addBeforeEnterListener(enterEvent -> {
                final Class<? extends Component> login = config.getView();
                if (!accessControl.isUserSignedIn() && !login.equals(enterEvent.getNavigationTarget()))
                    enterEvent.rerouteTo(login);
            });
        });
    }

}
