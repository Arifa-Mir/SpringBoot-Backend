package com.springboot.employee;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SpringBootBackendApplication{

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBackendApplication.class, args);
		System.out.println("Test SuccessFulluy1212");
	}
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

	

}
