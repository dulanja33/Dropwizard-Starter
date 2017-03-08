package com.example;

import com.example.resource.OrderResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * @author by Dulanja Wijethunga.
 */
public class OrderApplication extends Application<OrderConfiguration> {

    public static void main(String args[]) throws Exception {
        new OrderApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<OrderConfiguration> bootstrap) {
        super.initialize(bootstrap);
    }

    @Override
    public void run(OrderConfiguration configuration, Environment environment) throws Exception {
        environment.jersey().register(OrderResource.class);
    }
}
