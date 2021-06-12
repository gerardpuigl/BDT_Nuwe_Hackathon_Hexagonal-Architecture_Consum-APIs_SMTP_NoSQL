package io.nuwe.hackatonMWC.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfigurator implements WebMvcConfigurer {

	public MvcConfigurator() {
		super();
	}

	// Password encoder
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	// model Maper Bean
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

}