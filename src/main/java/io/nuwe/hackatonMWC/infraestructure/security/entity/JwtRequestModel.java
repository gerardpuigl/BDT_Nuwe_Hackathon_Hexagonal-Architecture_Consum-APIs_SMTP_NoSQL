package io.nuwe.hackatonMWC.infraestructure.security.entity;

import java.io.Serializable;

public class JwtRequestModel implements Serializable { 
	/**
	 * These models determine how our request and response formats would be for
	 * authentication. Below is the request model. As we can see, we shall be
	 * accepting two properties – username and password in our request.
	 */
	private static final long serialVersionUID = 2636936156391265891L;
	
	private String username;
	
	private String password;

	public JwtRequestModel() {
	}

	public JwtRequestModel(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	} 
	}