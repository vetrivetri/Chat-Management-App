package com.chat.management.gateway.chatmanagementgateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {
    @RequestMapping("/fallback/transaction")
    public ResponseEntity<String> fallback() {
        return ResponseEntity.ok("Chat Management Service is down. This is fallback.");
    }
}
