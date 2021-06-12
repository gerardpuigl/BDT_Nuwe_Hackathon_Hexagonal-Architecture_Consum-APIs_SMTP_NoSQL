package io.nuwe.hackatonMWC.dto;

import java.util.UUID;

import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
	
	private String id;
	
	private String name;
	
	private String username;
	
	private String email;
	
	private boolean isEmailVerified;
		
	private String gitUserId;
	
	private String countryId;
		
	public UserDTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isIsEmailVerified() {
		return isEmailVerified;
	}

	public void setIsEmailVerified(boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public String getGitUserId() {
		return gitUserId;
	}

	public void setGitUserId(String gitUserId) {
		this.gitUserId = gitUserId;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
		
}
