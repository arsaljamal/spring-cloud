package com.arsal.reservationservice;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

public class CustomLoadBalancerConfig {

    @Bean
    public ServiceInstanceListSupplier serviceInstanceListSupplier(ConfigurableApplicationContext context) {
        return ServiceInstanceListSupplier.builder()
                .withHints()
                .withBlockingDiscoveryClient()
                .withSameInstancePreference()
                .withZonePreference()
                .build(context);
    }
}
