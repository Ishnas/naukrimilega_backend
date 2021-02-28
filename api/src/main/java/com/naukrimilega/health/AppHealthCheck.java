package com.naukrimilega.health;

import com.codahale.metrics.health.HealthCheck;

public class AppHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        System.out.println("I'm healthy");
        return Result.healthy();
    }
}
