package com.james.demo.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.james.demo.dao.StudentRepo;
import com.james.demo.model.Student;

@Configuration
public class StudentConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(StudentRepo repo) {
		return args -> {
			Student james = new Student(
					"James",
					LocalDate.of(1994, 9, 8),
					"james.frh@gmail.com"
					);
			Student shermane = new Student(
					"Shermane",
					LocalDate.of(1992, 7, 15),
					"shermaneong@gmail.com"
					);
			repo.saveAll(List.of(james,shermane));

		};
	}

}
