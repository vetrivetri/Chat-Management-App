package com.chat.management.gateway.chatmanagementgateway.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


@Component
public class GatewayRateLimiterConfig  implements GatewayFilter {

   private final Cache<String, AtomicInteger> requestCounts = Caffeine.newBuilder()
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .build();

    private final int MAX_REQUESTS_PER_MINUTE = 100;

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            String clientId = extractClientId(exchange);

            AtomicInteger counter = requestCounts.get(clientId, key -> new AtomicInteger(0));
            int currentCount = counter.incrementAndGet();

            if (currentCount > MAX_REQUESTS_PER_MINUTE) {
                exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
                byte[] bytes = "Rate limit exceeded".getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                return exchange.getResponse().writeWith(Mono.just(buffer));
            }

            return chain.filter(exchange);
        }

        private String extractClientId(ServerWebExchange exchange) {
            return Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("x-api-client"))
                    .orElse("Unknown");
        }


}
