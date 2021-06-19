package io.nuwe.hackatonMWC.dto;

import java.util.List;

public class GitlabDTO {

	private String id;

	private String name;

	private String web_url;
	
	private String repositoriesURL;

	private List<GitlabRepoDTO> repositories;

	public GitlabDTO() {
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

	public String getWeb_url() {
		return web_url;
	}

	public void setWeb_url(String web_url) {
		this.web_url = web_url;
	}

	public List<GitlabRepoDTO> getRepositories() {
		return repositories;
	}

	public void setRepositories(List<GitlabRepoDTO> repositories) {
		this.repositories = repositories;
	}

	public String getRepositoriesURL() {
		return repositoriesURL;
	}

	public void setRepositoriesURL(String repositoriesURL) {
		this.repositoriesURL = repositoriesURL;
	}

}
