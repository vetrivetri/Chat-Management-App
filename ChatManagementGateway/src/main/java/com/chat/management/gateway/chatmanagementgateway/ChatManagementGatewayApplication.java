package com.chat.management.gateway.chatmanagementgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration
@EnableScheduling
public class ChatManagementGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatManagementGatewayApplication.class, args);
	}

}
