package io.nuwe.hackatonMWC.domain;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "gitprofile")
public class GitProfile {

	private String id;
	
	private String username;
	
	private String url;

	private GitProfileType type;
	
	private List<String> repositories;
	
	private List<String> repositoriesURL;
	
	public GitProfile() {
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public GitProfileType getType() {
		return type;
	}

	public void setType(GitProfileType type) {
		this.type = type;
	}

	public List<String> getRepositories() {
		return repositories;
	}

	public void setRepositories(List<String> repositories) {
		this.repositories = repositories;
	}

	public List<String> getRepositoriesURL() {
		return repositoriesURL;
	}

	public void setRepositoriesURL(List<String> repositoriesURL) {
		this.repositoriesURL = repositoriesURL;
	}


}
