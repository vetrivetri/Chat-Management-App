package com.chat.management.gateway.chatmanagementgateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class GatewayRateLimiterConfig  implements GatewayFilter {

        private final Map<String, AtomicInteger> requestCounts = new ConcurrentHashMap<>();
        private final int MAX_REQUESTS_PER_MINUTE = 1000;

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            String clientId = extractClientId(exchange); // e.g., IP or x-api-key

            requestCounts.putIfAbsent(clientId, new AtomicInteger(0));
            int currentCount = requestCounts.get(clientId).incrementAndGet();

            if (currentCount > MAX_REQUESTS_PER_MINUTE) {
                exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
                byte[] bytes = "Rate limit exceeded".getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                return exchange.getResponse().writeWith(Mono.just(buffer));
            }

            return chain.filter(exchange);
        }

        private String extractClientId(ServerWebExchange exchange) {
            return exchange.getRequest().getHeaders().getFirst("x-api-client");
        }

        @Scheduled(fixedRate = 60000)
        public void resetCounts() {
            requestCounts.clear();
        }

}
