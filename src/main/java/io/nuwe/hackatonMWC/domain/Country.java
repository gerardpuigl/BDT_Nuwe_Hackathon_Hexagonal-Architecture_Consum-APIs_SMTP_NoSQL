package io.nuwe.hackatonMWC.domain;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "country")
public class Country {

	private String id;
	
	private String name;
	
	private String[] alphaCode;
	
	private int callingCode;

	public Country() {
		alphaCode = new String[2];
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
		return alphaCode[0];
	}
	
	public String getAlpha3Code() {
		return alphaCode[1];
	}

	public void setAlpha2Code(String alphaCode) {
		this.alphaCode[0] = alphaCode;
	}
	
	public void setAlpha3Code(String alphaCode) {
		this.alphaCode[1] = alphaCode;
	}
	
	public void setAlphaCode(String[] alphaCode) {
		this.alphaCode = alphaCode;
	}

	
	public int getCallingCode() {
		return callingCode;
	}

	public void setCallingCode(int callingCode) {
		this.callingCode = callingCode;
	}

}
