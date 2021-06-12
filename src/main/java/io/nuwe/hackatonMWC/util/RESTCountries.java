package io.nuwe.hackatonMWC.util;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import io.nuwe.hackatonMWC.domain.Country;

@Component
public class RESTCountries {

	private List<Country> countryList;
	
    @Autowired
    WebClient webClient;
    
    @PostConstruct
	private void FillCountryList_WhenStartAPP() {
		countryList = webClient.get()
			.uri("https://restcountries.eu/rest/v2/all")
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToFlux(Country.class)
			.filter(c->c.getCallingCodes().length > 1)
			.buffer().blockLast();
		SetIdWithCode2(countryList);
	}
    
    private void SetIdWithCode2(List<Country> countryList) {
    	countryList.stream()
    	.peek(c->c.setId(c.getAlpha2Code()))
    	.collect(Collectors.toList());    	
    }

	public List<Country> getCountryList() {
		return countryList;
	}
	
	public Country getById(String id) {
		Country country = countryList.stream().filter(c->c.getId().equals("id")).findFirst().get();
		return country;
	}

}
