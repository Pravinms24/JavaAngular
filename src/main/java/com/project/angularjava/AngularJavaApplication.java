package com.project.angularjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@ComponentScan({ "com.project" })
@SpringBootApplication(scanBasePackages = { "com.project.angularjava" })
@EnableMongoRepositories(basePackages = { "com.project.angularjava" })
//@SpringBootApplication
public class AngularJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AngularJavaApplication.class, args);
	}

}
