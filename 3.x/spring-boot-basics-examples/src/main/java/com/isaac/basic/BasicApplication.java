package com.isaac.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasicApplication {

	public static void main(String[] args) {
		RunExamples.start();
		SpringApplication.run(BasicApplication.class, args);
	}

}
