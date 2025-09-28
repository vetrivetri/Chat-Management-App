package com.chat.management.gateway.chatmanagementgateway.config;

import com.chat.management.gateway.chatmanagementgateway.beans.GatewayGenericResponse;
import com.chat.management.gateway.chatmanagementgateway.exception.GatewayException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Service
public class ChatManagementSecurityFilter extends AbstractGatewayFilterFactory<AbstractGatewayFilterFactory.NameConfig> {
    private static final Logger logger = LoggerFactory.getLogger(ChatManagementSecurityFilter.class);

    @Value("${api.gateway.auth.key}")
    private String secureApiKey;

    @Override
    public GatewayFilter apply(NameConfig config) {
       return((exchange, chain) ->  {
            try {
                String apiKey = exchange.getRequest().getHeaders().getFirst("x-api-auth-key");

                if (apiKey == null || !apiKey.equals(secureApiKey)) {
                    throw new GatewayException(HttpStatus.UNAUTHORIZED,"API-GATEWAY-ERR","Invalid Or Missing API Key");
                }

            } catch (GatewayException ex) {
                logger.error("Exception in filter: {}", ex.getMessage(), ex);

                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                exchange.getResponse().getHeaders().add("Content-Type", "application/json");

                byte[] bytes = null;
                try {
                    bytes = (new ObjectMapper().writeValueAsBytes(new GatewayGenericResponse(HttpStatus.UNAUTHORIZED.toString(),ex.getErrorMessage(),ex.getErrorCode())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);

                return exchange.getResponse().writeWith(Mono.just(buffer));

            }
            return chain.filter(exchange);
        });
    }
}
