package com.example.ballen.auth;

import com.example.ballen.core.auth.AccessControl;
import com.example.ballen.core.auth.CurrentUser;

/**
 * Default mock implementation of {@link AccessControl}. This implementation
 * accepts any string as a password, and considers the user "admin" as the only
 * administrator.
 */
public class MockAccessControl implements AccessControl {

    @Override
    public boolean signIn(final String username, final String password) {
        if (username == null || username.isEmpty()) {
            return false;
        }

        CurrentUser.set(username);
        return true;
    }

    @Override
    public boolean isUserSignedIn() {
        return !CurrentUser.get().isEmpty();
    }

    @Override
    public boolean isUserInRole(final String role) {
        if (ADMIN_ROLE_NAME.equals(role)) {
            // Only the "admin" user is in the "admin" role
            return ADMIN_USERNAME.equals(getPrincipalName());
        }

        // All users are in all non-admin roles
        return true;
    }

    @Override
    public String getPrincipalName() {
        return CurrentUser.get();
    }

}
