package com.example.ballen.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ballen.core.auth.AccessControl;
import com.example.ballen.core.auth.AccessControlFactory;
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

    @Override
    public void serviceInit(final ServiceInitEvent initEvent) {
        final AccessControl accessControl = AccessControlFactory.getInstance().createAccessControl();

        initEvent.getSource().addUIInitListener(uiInitEvent -> {
            uiInitEvent.getUI().addBeforeEnterListener(enterEvent -> {
                if (!accessControl.isUserSignedIn() && !config.getView().equals(enterEvent.getNavigationTarget()))
                    enterEvent.rerouteTo(config.getView());
            });
        });
    }

}
