package com.example.resource;

import com.example.beans.Orders;
import com.example.dao.OrderDao;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

/**
 * @author by Dulanja Wijethunga.
 */
@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

    private OrderDao orderDao;

    public OrderResource(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @GET
    @Path("/order")
    @UnitOfWork
    public List<Orders> getAllOrder() {
        return orderDao.findAll();
    }

    @GET
    @Path("/order/{id}")
    @UnitOfWork
    public Orders getOrder(@PathParam("id") LongParam id) {
        Optional<Orders> order = orderDao.findById(id.get());
        return order.isPresent() ? order.get() : null;
    }

    @POST
    @Path("/order")
    @UnitOfWork
    public Orders createOrder(Orders orders) {
        return orderDao.createOrder(orders);
    }
}
