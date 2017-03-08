package com.example.auth;

import com.example.beans.User;
import io.dropwizard.auth.Authorizer;

/**
 * @author by Dulanja Wijethunga.
 */
public class OrderAuthorizer implements Authorizer<User> {
    @Override
    public boolean authorize(User user, String role) {
        return user.getRoles() != null && user.getRoles().contains(role);
    }
}
