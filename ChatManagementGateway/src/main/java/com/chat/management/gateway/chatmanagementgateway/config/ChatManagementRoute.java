package com.chat.management.gateway.chatmanagementgateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ChatManagementRoute  {
@Autowired
ChatManagementSecurityFilter chatManageFilter;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("chat-management-service", r -> r.path("/v1/chat/**")
                        .filters(f ->f.filter(chatManageFilter.apply(chatManageFilter.newConfig())))
                        .uri("lb://Chat-Management-App"))
                .build();
    }

}
