package org.example.logistics.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth_route", r -> r.path("/api/auth/**")
                        .uri("lb://AUTH-SERVICE"))
                .route("shipment_route", r -> r.path("/api/shipments/**")
                        .uri("lb://SHIPMENT-SERVICE"))
                .route("courier_route", r -> r.path("/api/couriers/**")
                        .uri("lb://COURIER-SERVICE"))
                .build();
    }
}
