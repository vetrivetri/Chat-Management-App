package com.chat.management.gateway.chatmanagementgateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ChatManagementRoute  {
@Autowired
ChatManagementSecurityFilter chatManagementSecurityFilter;
    @Autowired
    GatewayRateLimiterConfig gatewayRateLimiterConfig;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("chat-management-service",
                        r -> r.path("/v1/chat/**")
                        .filters(f ->f.filter(chatManagementSecurityFilter.apply(
                                new AbstractGatewayFilterFactory.NameConfig ())).filter(new GatewayRateLimiterConfig()))
                        .uri("lb://ChatManagementApp"))
                .build();
    }

}
