package io.nuwe.hackatonMWC.infraestructure.dto;

import java.util.List;

public class GithubDTO {
	
	private String id;
	
	private String name;
	
	private String url;
		
	private String repos_url;

	private List<GithubRepoDTO> repositories;

	public GithubDTO() {
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<GithubRepoDTO> getRepositories() {
		return repositories;
	}

	public void setRepositories(List<GithubRepoDTO> repositories) {
		this.repositories = repositories;
	}

	public String getRepos_url() {
		return repos_url;
	}

	public void setRepos_url(String repos_url) {
		this.repos_url = repos_url;
	}
}
