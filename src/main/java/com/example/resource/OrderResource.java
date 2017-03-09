package com.example.resource;

import com.example.beans.Orders;
import com.example.beans.User;
import com.example.dao.OrderDao;
import com.example.filter.OrderIdValidate;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api("The Order Microservice")
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
    @ApiOperation(value = "Get All Orders",
            notes = "Get all orders",
            response = Orders.class, responseContainer = "Order"
    )
    public List<Orders> getAllOrder() {
        return orderDao.findAll();
    }

    @GET
    @Path("/order/{id}")
    @RolesAllowed({"ADMIN", "BASIC_USER"})
    @UnitOfWork
    @OrderIdValidate
    @ApiOperation(value = "Get Order",
            notes = "Get order by given id",
            response = Orders.class, responseContainer = "Order"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Request successfully handled", response = Orders.class),
                    @ApiResponse(code = 400, message = "Invalid parameter(s)", response = RuntimeException.class)
            }
    )
    public Orders getOrder(@ApiParam(value = "id") @PathParam("id") LongParam id) {
        Optional<Orders> order = orderDao.findById(id.get());
        return order.isPresent() ? order.get() : null;
    }

    @POST
    @Path("/order")
    @RolesAllowed("ADMIN")
    @UnitOfWork
    @ApiOperation(value = "Put Order",
            notes = "Put order by for given description",
            response = Orders.class, responseContainer = "Order"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Request successfully handled", response = Orders.class),
                    @ApiResponse(code = 400, message = "Invalid parameter(s)", response = RuntimeException.class)
            }
    )
    public Orders createOrder(@ApiParam(hidden=true) @Auth User user, @ApiParam(value = "Order") Orders orders) {
        LOGGER.debug("Logged in user:{}", user);
        return orderDao.createOrder(orders);
    }
}
