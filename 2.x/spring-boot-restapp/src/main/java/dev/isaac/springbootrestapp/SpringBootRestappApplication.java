package dev.isaac.springbootrestapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class SpringBootRestappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestappApplication.class, args);
	}

}
