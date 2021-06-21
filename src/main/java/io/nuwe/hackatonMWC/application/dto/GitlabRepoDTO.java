package io.nuwe.hackatonMWC.application.dto;

public class GitlabRepoDTO {

	private int id;
	
	private String name;
	
	private String description;
	
	private String web_url;
	
	public GitlabRepoDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWeb_url() {
		return web_url;
	}

	public void setWeb_url(String web_url) {
		this.web_url = web_url;
	}
}
