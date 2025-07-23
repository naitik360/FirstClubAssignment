package com.membership.subscriptionmembership;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Membership API",
				version = "1.0",
				description = "Membership Program Management"
		)
)
public class MembershipProgramApplication {
	public static void main(String[] args) {
		SpringApplication.run(MembershipProgramApplication.class, args);
	}
}
