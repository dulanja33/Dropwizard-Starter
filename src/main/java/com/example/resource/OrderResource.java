package com.example.resource;

import com.example.beans.Orders;
import com.example.beans.User;
import com.example.dao.OrderDao;
import com.example.filter.OrderIdValidate;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderResource.class);

    private OrderDao orderDao;

    public OrderResource(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @GET
    @Path("/order")
    @PermitAll
    @UnitOfWork
    public List<Orders> getAllOrder() {
        return orderDao.findAll();
    }

    @GET
    @Path("/order/{id}")
    @RolesAllowed({"ADMIN", "BASIC_USER"})
    @UnitOfWork
    @OrderIdValidate
    public Orders getOrder(@PathParam("id") LongParam id) {
        Optional<Orders> order = orderDao.findById(id.get());
        return order.isPresent() ? order.get() : null;
    }

    @POST
    @Path("/order")
    @RolesAllowed("ADMIN")
    @UnitOfWork
    public Orders createOrder(@Auth User user, Orders orders) {
        LOGGER.info("Logged in user:{}", user);
        return orderDao.createOrder(orders);
    }
}
