package io.nuwe.hackatonMWC.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "country")
public class Country {

	private String id;
	
	private String name;
	
	private String alpha2Code;
	
	private String alpha3Code;
	
	private String[] callingCodes;

	public Country() {
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

	public String getAlpha2Code() {
		return alpha2Code;
	}
	
	public String getAlpha3Code() {
		return alpha3Code;
	}

	public void setAlpha2Code(String alphaCode) {
		this.alpha2Code = alphaCode;
	}
	
	public void setAlpha3Code(String alphaCode) {
		this.alpha3Code = alphaCode;
	}

	public String[] getCallingCodes() {
		return callingCodes;
	}

	public void setCallingCodes(String[] callingCodes) {
		this.callingCodes = callingCodes;
	}
}
