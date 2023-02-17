package com.copilot.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.copilot.sample.repository")
@EntityScan(basePackages = { "com.copilot.sample.model" })
public class CopilotDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(CopilotDemoApplication.class, args);
	}

}
