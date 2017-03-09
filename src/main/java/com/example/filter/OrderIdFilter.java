package com.example.filter;

import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * @author by Dulanja Wijethunga.
 */
public class OrderIdFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        List<String> params = requestContext.getUriInfo().getPathParameters().get("id");
        if (params.size() != 1 || !StringUtils.isNumeric(params.get(0))) {
            throw new WebApplicationException("Invalid Order Id",new IllegalArgumentException("Invalid Order Id"),
                    Response.Status.BAD_REQUEST);
        }
    }
}

