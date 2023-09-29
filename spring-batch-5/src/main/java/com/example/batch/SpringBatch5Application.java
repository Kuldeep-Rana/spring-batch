package com.example.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.persistence.EntityManagerFactory;

/**
 * This example is running spring batch with item reader and writer using partitioning and chunk
 **/

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatch5Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBatch5Application.class, args);
	}

}
