package com.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

/**
 * @author by Dulanja Wijethunga.
 */
public class OrderApplication extends Application<OrderConfiguration> {

    public static void main(String args[]) throws Exception {
        new OrderApplication().run(args);
    }

    @Override
    public void run(OrderConfiguration configuration, Environment environment) throws Exception {

    }
}
