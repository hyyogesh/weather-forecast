package io.yogesh.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import io.yogesh.api.config.SwaggerConfig;
import io.yogesh.api.config.WebConfig;

@SpringBootApplication
@Import({SwaggerConfig.class,WebConfig.class})
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}
}
