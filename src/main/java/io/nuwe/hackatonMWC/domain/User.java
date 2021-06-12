package io.nuwe.hackatonMWC.domain;

import java.util.UUID;

import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.password.PasswordEncoder;

@Document(collection = "user")
public class User {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private UUID id;
	
	private String name;
	
	private String username;
	
	@Email(message="Email no valid.")
	private String email;
	
	private boolean isEmailVerified;
	
	private String password;
	
	private String gitLabUserId;
	
	private String countryId;

	public User() {
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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

	public boolean isEmailVerified() {
		return isEmailVerified;
	}

	public void setEmailVerified(boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = passwordEncoder.encode(password);
	}

	public String getGitLabUserIdString() {
		return gitLabUserId;
	}

	public void setGitLabUserIdString(String gitLabUserId) {
		this.gitLabUserId = gitLabUserId;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	
}
