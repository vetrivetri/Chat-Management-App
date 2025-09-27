package com.chat.management.gateway.chatmanagementgateway.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GatewayException {
    private HttpStatus httpStatus;
    private String errorCode;
    private String errorMessage;
}
