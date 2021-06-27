package io.nuwe.hackatonMWC.infraestructure.apis;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import io.nuwe.hackatonMWC.infraestructure.dto.CountryDTO;

@Component
public class ApiRESTCountries {

	private List<CountryDTO> countryList;
	
    @Autowired
    WebClient webClient;
    
    @PostConstruct
	private void FillCountryList_WhenStartAPP() {
		countryList = webClient.get()
			.uri("https://restcountries.eu/rest/v2/all")
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToFlux(CountryDTO.class)
			.buffer().blockLast();
		setIdWithCode2(countryList);
	}
    
    private void setIdWithCode2(List<CountryDTO> countryList) {
    	countryList.stream()
    	.peek(c->c.setId(c.getAlpha2Code()))
    	.collect(Collectors.toList());    	
    }

	public List<CountryDTO> getCountryList() {
		return countryList;
	}
	
	public CountryDTO getById(String id) {
		CountryDTO country = countryList.stream().filter(c->c.getId().equals("id")).findFirst().get();
		return country;
	}

}
