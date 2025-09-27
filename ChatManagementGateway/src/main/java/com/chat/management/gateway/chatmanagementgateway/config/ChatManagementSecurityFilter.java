package com.chat.management.gateway.chatmanagementgateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class ChatManagementSecurityFilter extends AbstractGatewayFilterFactory<AbstractGatewayFilterFactory.NameConfig> {

    @Override
    public GatewayFilter apply(NameConfig config) {
       return((exchange, chain) ->  {
            try {

            } catch (Exception ex) {

            }
            return chain.filter(exchange);
        });
    }
}
