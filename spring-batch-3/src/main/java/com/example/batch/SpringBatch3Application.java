package com.example.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * This example is running spring batch with Spring scheduler
 **/

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
public class SpringBatch3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatch3Application.class, args);
	}

}
