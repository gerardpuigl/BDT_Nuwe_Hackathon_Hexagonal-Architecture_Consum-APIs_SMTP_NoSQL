package io.nuwe.hackatonMWC.infraestructure.repositorys.postgresql.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity(name="users")
public class PostgreUserEntity {
	
    @Id
    @GeneratedValue(generator = "uuid" )
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id")
	private String id;
	
	private String name;

	private String username;
	
	private String email;
	
	private boolean isEmailVerified;
	
	private String password;
	
	private String githubUserId;
	
	private String gitlabUserId;
	
	private String countryId;

	public PostgreUserEntity() {
	}
	
	public PostgreUserEntity(String id, String name, String username, @Email(message = "Email no valid.") String email,
			boolean isEmailVerified, String password, String githubUserId, String gitlabUserId, String countryId) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.isEmailVerified = isEmailVerified;
		this.password =  password;
		this.githubUserId = githubUserId;
		this.gitlabUserId = gitlabUserId;
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

	public boolean isEmailVerified() {
		return isEmailVerified;
	}

	public void setEmailVerified(boolean isEmailVerified) {
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
	
	@JsonIgnore
	public boolean isNew() {
		return (getId() == null);
	}
}
