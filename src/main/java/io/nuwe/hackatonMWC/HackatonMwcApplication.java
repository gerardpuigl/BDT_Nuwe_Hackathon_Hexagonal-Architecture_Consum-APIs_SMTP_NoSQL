package io.nuwe.hackatonMWC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class HackatonMwcApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackatonMwcApplication.class, args);
	}

}
