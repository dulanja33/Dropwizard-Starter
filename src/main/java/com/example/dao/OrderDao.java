package com.example.dao;

import com.example.beans.Orders;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

/**
 * @author by Dulanja Wijethunga.
 */
public class OrderDao extends AbstractDAO<Orders> {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public OrderDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Orders> findById(long id) {
        return Optional.ofNullable(get(id));
    }

    public Orders createOrder(Orders orders) {
        return persist(orders);
    }

    public List<Orders> findAll() {
        Query query = currentSession().createQuery("SELECT E FROM Orders E");
        return list(query);
    }
}
