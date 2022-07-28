package com.sparta.week02_homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Week02HomeworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(Week02HomeworkApplication.class, args);
	}

}
