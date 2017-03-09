package com.example.filter;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

/**
 * @author by Dulanja Wijethunga.
 */
@Provider
public class OrderIdValidateFeature implements DynamicFeature {
    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        if (resourceInfo.getResourceMethod().getAnnotation(OrderIdValidate.class) != null) {
            context.register(OrderIdFilter.class);
        }
    }
}
