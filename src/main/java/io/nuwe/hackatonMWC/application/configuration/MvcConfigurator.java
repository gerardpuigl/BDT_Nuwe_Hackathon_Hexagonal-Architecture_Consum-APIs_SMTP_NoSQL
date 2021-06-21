package io.nuwe.hackatonMWC.application.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfigurator implements WebMvcConfigurer {

	public MvcConfigurator() {
		super();
	}

	// model Maper Bean
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

}