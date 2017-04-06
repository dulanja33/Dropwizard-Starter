package com.example;

import io.dropwizard.Configuration;

/**
 * @author by Dulanja Wijethunga.
 */
public class OrderConfiguration extends Configuration {
    private String demoApp;

    public String getDemoApp() {
        return demoApp;
    }

    public void setDemoApp(String demoApp) {
        this.demoApp = demoApp;
    }
}
