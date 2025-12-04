package com.chat.management.app.chatManagementApp;

import com.chat.management.app.chatManagementApp.service.impl.ChatManagementServiceImpl;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Map;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.chat.management.app",
"com.chat.management.app.chatManagementApp.controller",
"com.chat.management.app.chatManagementApp.service"})
@EnableJpaRepositories()
public class ChatManagementApp {

	public static void main(String[] args) {
		SpringApplication.run(ChatManagementApp.class, args);
	}

}
