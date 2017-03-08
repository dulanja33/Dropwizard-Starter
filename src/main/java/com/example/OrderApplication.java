package com.example;

import com.example.beans.Orders;
import com.example.dao.OrderDao;
import com.example.resource.OrderResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
    }

    @Override
    public void run(OrderConfiguration configuration, Environment environment) throws Exception {
        final OrderDao dao = new OrderDao(hibernateBundle.getSessionFactory());
        environment.jersey().register(new OrderResource(dao));
    }
}
