package com.teksenz.studentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class StudentService {

	public static void main(String[] args) {
		SpringApplication.run(StudentService.class, args);
	}

}
