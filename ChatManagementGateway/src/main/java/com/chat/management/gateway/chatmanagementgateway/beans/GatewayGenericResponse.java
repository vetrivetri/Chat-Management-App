package com.chat.management.gateway.chatmanagementgateway.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GatewayGenericResponse {
    private String httpStatusCode;
    private String message;
    private String code;
}
