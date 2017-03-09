package com.example;

import com.example.auth.OrderAuthenticator;
import com.example.auth.OrderAuthorizer;
import com.example.beans.Orders;
import com.example.beans.User;
import com.example.dao.OrderDao;
import com.example.filter.OrderIdValidateFeature;
import com.example.health.OrderHealthCheck;
import com.example.resource.OrderResource;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.paradoxical.dropwizard.swagger.SwaggerConfiguration;
import io.paradoxical.dropwizard.swagger.SwaggerUIConfigurator;
import io.paradoxical.dropwizard.swagger.bundles.SwaggerUIBundle;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

/**
 * @author by Dulanja Wijethunga.
 */
public class OrderApplication extends Application<OrderConfiguration> {

    private final HibernateBundle<OrderConfiguration> hibernateBundle =
            new HibernateBundle<OrderConfiguration>(Orders.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(OrderConfiguration configuration) {
                    return configuration.getDatabase();
                }
            };
    private final MigrationsBundle<OrderConfiguration> migrationsBundle =
            new MigrationsBundle<OrderConfiguration>() {
                @Override
                public DataSourceFactory getDataSourceFactory(OrderConfiguration configuration) {
                    return configuration.getDatabase();
                }
            };

    public static void main(String args[]) throws Exception {
        new OrderApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<OrderConfiguration> bootstrap) {
        bootstrap.addBundle(migrationsBundle);
        bootstrap.addBundle(hibernateBundle);

        SwaggerConfiguration configuration = new SwaggerConfiguration();
        configuration.setTitle("Order MicroService");
        configuration.setDescription("Sample Order System");
        configuration.setResourcePackage("com.example.resource");
        configuration.setVersion("1.0");
        configuration.setScan(true);

        bootstrap.addBundle(new SwaggerUIBundle(SwaggerUIConfigurator.forConfig(configuration)));
    }

    @Override
    public void run(OrderConfiguration configuration, Environment environment) throws Exception {
        final OrderDao dao = new OrderDao(hibernateBundle.getSessionFactory());
        environment.jersey().register(new OrderResource(dao));
        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new OrderAuthenticator())
                .setAuthorizer(new OrderAuthorizer())
                .setRealm("SUPER SECRET STUFF")
                .buildAuthFilter()));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(OrderIdValidateFeature.class);
        environment.healthChecks().register("order", new OrderHealthCheck());
    }
}
