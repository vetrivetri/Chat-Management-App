package com.chat.management.gateway.chatmanagementgateway.exception;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;

@Component
public class GatewayExceptionHandler extends AbstractErrorWebExceptionHandler {

    public GatewayExceptionHandler(ErrorAttributes errorAttributes,
                              WebProperties webProperties,
                              ApplicationContext applicationContext,
                              ServerCodecConfigurer codecConfigurer) {
        super(errorAttributes, webProperties.getResources(), applicationContext);
        super.setMessageWriters(codecConfigurer.getWriters());
        super.setMessageReaders(codecConfigurer.getReaders());
    }


    protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        Map<String, Object> errorAttributes = getErrorAttributes(request, ErrorAttributeOptions.defaults());

        HttpStatus status = HttpStatus.valueOf((int) errorAttributes.getOrDefault("status", 500));
        String message = (String) errorAttributes.getOrDefault("message", "Unexpected error");

        Map<String, Object> responseBody = Map.of(
                "timestamp", LocalDateTime.now(),
                "status", status.value(),
                "error", status.getReasonPhrase(),
                "message", message,
                "path", request.path()
        );

        return ServerResponse.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(responseBody);
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
      return RouterFunctions.route(RequestPredicates.all(),this::renderErrorResponse);
    }
}
