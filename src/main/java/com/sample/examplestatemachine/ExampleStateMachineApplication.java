package com.sample.examplestatemachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(value = "com.sample.examplestatemachine.repository")
public class ExampleStateMachineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleStateMachineApplication.class, args);
	}
}
