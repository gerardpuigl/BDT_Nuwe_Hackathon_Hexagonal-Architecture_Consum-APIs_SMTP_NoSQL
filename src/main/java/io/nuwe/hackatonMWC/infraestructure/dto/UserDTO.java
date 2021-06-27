package io.nuwe.hackatonMWC.infraestructure.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
	
	private String id;
	
	private String name;
	
	private String username;
	
	private String email;
	
	private boolean isEmailVerified;
	
	private String githubUserId;
	
	private String gitlabUserId;
	
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

	public String getGithubUserId() {
		return githubUserId;
	}

	public void setGithubUserId(String githubUserId) {
		this.githubUserId = githubUserId;
	}

	public String getGitlabUserId() {
		return gitlabUserId;
	}

	public void setGitlabUserId(String gitlabUserId) {
		this.gitlabUserId = gitlabUserId;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
		
}
