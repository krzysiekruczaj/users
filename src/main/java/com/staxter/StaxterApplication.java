package com.staxter;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class StaxterApplication {

	public static void main(String[] args) {
		SpringApplication.run(StaxterApplication.class, args);
	}

}
