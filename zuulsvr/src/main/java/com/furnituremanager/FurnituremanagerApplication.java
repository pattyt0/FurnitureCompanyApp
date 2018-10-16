package com.furnituremanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class FurnituremanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FurnituremanagerApplication.class, args);
	}
}
