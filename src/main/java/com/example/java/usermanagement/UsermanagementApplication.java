package com.example.java.usermanagement;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "User Management Service",
				description = "This is a user management service",
				version = "V2.0",
				contact = @Contact(
						name = "Joe",
						email = "joe@gmail.com",
						url = "https://www.backafile.com"
				),
				license = @License(
						name = "Not yet"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "User management external docs",
				url = "https://www.backafile.com"
		)
)
public class UsermanagementApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}


	public static void main(String[] args) {
		SpringApplication.run(UsermanagementApplication.class, args);
	}

}
