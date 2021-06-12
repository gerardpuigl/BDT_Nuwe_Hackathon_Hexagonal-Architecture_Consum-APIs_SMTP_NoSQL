package io.nuwe.hackatonMWC.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Document(collection = "user")
public class User {
	
	@Id
	private String id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String username;
	
	@Email(message="Email no valid.")
	@NotBlank
	private String email;
	
	private boolean isEmailVerified;
	
	@NotBlank
	private String password;
	
	private String gitUserId;
	
	private String countryId;

	public User() {
	}
	
	public User(String id, String name, String username, @Email(message = "Email no valid.") String email,
			boolean isEmailVerified, String password, String gitUserId, String countryId) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.isEmailVerified = isEmailVerified;
		this.password =  password;
		this.gitUserId = gitUserId;
		this.countryId = countryId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	@JsonIgnore
	public boolean isNew() {
		return (getId() == null);
	}
}
