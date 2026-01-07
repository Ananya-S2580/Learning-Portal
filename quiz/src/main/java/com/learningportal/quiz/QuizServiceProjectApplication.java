package com.learningportal.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuizServiceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizServiceProjectApplication.class, args);
	}

}
