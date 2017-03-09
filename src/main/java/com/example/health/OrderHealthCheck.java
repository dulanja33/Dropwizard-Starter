package com.example.health;

import com.codahale.metrics.health.HealthCheck;


/**
 * @author by Dulanja Wijethunga.
 */
public class OrderHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return Result.healthy(String.valueOf(System.currentTimeMillis()));
    }
}