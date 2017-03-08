package com.example.resource;

import com.example.beans.Order;
import io.dropwizard.jersey.params.IntParam;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author by Dulanja Wijethunga.
 */
@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

    private List<Order> orderList;

    public OrderResource() {
        Order order1 = new Order(1, "pizza");
        Order order2 = new Order(2, "submarine");
        orderList = Arrays.asList(order1, order2);
    }

    @GET
    @Path("/order")
    public List<Order> getAllOrder() {
        return orderList;
    }

    @GET
    @Path("/order/{id}")
    public Order getOrder(@PathParam("id") IntParam id) {
        Optional<Order> filteredOrder = orderList.stream()
                .filter(order -> order.getOrderId().equals(id.get()))
                .findFirst();
        return filteredOrder.isPresent() ? filteredOrder.get() : null;
    }

    @POST
    @Path("/order")
    public Order createOrder(Order order) {
        return order;
    }
}
