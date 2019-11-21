package io.devxchange.logmetrics.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "io.devxchange.logmetrics.demo", "io.devxchange.logmetrics" })
public class LoggingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoggingDemoApplication.class, args);
	}
}
