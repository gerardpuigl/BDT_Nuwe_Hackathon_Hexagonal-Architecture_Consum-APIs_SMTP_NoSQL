package io.nuwe.hackatonMWC.infraestructure.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.nuwe.hackatonMWC.infraestructure.security.jwtutils.JwtAuthenticationEntryPoint;
import io.nuwe.hackatonMWC.infraestructure.security.jwtutils.JwtFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtFilter filter;

	/**
	 * We provide the BcryptPasswordEncoder instance by implementing a method that
	 * generates the same. We annotate the method with @Bean to add to our Spring
	 * Context.
	 */
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	/**
	 * AuthenticationManager is required bean for our Jwt controller class and add the filter we
	 * just created to our configuration. We are also going to configure which
	 * requests are to be authenticated and which are not to be. We shall also add
	 * the AuthenticationEntryPoint to our requests to send back the 401 error
	 * response. Since, we also do not need to maintain session variables while
	 * using jwt we can make our session STATELESS.
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf()
			.disable()
			.authorizeRequests()
			
	/*
			.anyRequest()
			.permitAll();
		*/
			.antMatchers("/login","/register","/swagger-ui/**","/documentation.html","/swagger-ui.html","/v3/api-docs/**")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.exceptionHandling()
			.authenticationEntryPoint(authenticationEntryPoint)
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}
}
