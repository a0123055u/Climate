package com.example.climate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClimateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClimateApplication.class, args);
		
	}

}
