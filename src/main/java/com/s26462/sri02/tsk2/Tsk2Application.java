package com.s26462.sri02.tsk2;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Tsk2Application {

	public static void main(String[] args) {
		SpringApplication.run(Tsk2Application.class, args);
	}

	@Bean
	public ModelMapper modelMapper() { return new ModelMapper(); }
}
