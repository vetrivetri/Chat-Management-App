package com.chat.management.app.ChatManagementApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration
@ComponentScan("com.chat.management.app.*mv ")
public class ChatManagementApp {

	public static void main(String[] args) {
		SpringApplication.run(ChatManagementApp.class, args);
	}

}
