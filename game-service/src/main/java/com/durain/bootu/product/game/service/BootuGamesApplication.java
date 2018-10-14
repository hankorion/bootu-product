package com.durain.bootu.product.game.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class BootuGamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootuGamesApplication.class, args);
	}
}
