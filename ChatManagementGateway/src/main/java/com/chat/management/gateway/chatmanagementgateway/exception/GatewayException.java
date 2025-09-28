package com.chat.management.gateway.chatmanagementgateway.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GatewayException extends Exception {
    private HttpStatus httpStatus;
    private String errorCode;
    private String errorMessage;
}
