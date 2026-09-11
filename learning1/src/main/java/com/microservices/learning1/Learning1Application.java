package com.microservices.learning1;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Account Microservices REST API Dcumnetation",
				description = "Talha Account Microservice documnetaiton",
				version = "v1",
				contact = @Contact(
						name = "Talha Helal",
						email = "talhamallick444@gmail.com",
						url="https://github.com/mallick-talha/microsevices"
				)
		),
		externalDocs = @ExternalDocumentation(
				url = "https://azure.microsoft.com/"
)
)
public class Learning1Application {

	public static void main(String[] args) {
		SpringApplication.run(Learning1Application.class, args);
	}

}
