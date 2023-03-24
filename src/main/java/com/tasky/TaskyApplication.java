package com.tasky;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(servers = @Server(url = "/", description = "Default Server URL"))
@SpringBootApplication
public class TaskyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskyApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new  ModelMapper();
	}


}
