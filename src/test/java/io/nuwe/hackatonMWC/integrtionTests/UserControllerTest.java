package io.nuwe.hackatonMWC.integrtionTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import cat.itacademy.proyectoerp.repository.IOrderRepository;
import cat.itacademy.proyectoerp.repository.UserRepository;

/**
 * This class testing the Integration of Stats Endpoints. We load some data
 * directly to the test database to work with it.
 * 
 * @Transactional used to load scripts @Sql only one time.
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	UserRepository userRepository;

	// Test /api/v1/register
	@Test
	@DisplayName("Correct [POST] /api/v1/register")
	public void testRegisterUser() throws Exception {

		// request preparation
		String uri = "/api/v1/register"; // request uri
		String json = "{\"username\":\"testUser\""
				+ "\"password\":\"hackathonMWC\""
				+ "}"; // json
		
		// request
		mockMvc.perform(post(uri) //method & url
				.contentType(MediaType.APPLICATION_JSON).content(json) //data send
				.accept(MediaType.APPLICATION_JSON_VALUE)) //data received

				// results
				.andExpect(status().isOk());
	}
	
	// Test /api/v1/login
	@Test
	@DisplayName("Correct [POST] /api/v1/login")
	public void testLoginUser() throws Exception {

		// request preparation
		String uri = "/api/v1/login"; // request uri
		String json = "{\"username\":\"testUser\""
				+ "\"password\":\"hackathonMWC\""
				+ "}"; // json
		
		// request
		mockMvc.perform(post(uri) //method & url
				.contentType(MediaType.APPLICATION_JSON).content(json) //data send
				.accept(MediaType.APPLICATION_JSON_VALUE)) //data received

				// results
				.andExpect(status().isOk());
	}
	


}
