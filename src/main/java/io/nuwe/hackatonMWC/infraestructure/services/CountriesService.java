package io.nuwe.hackatonMWC.infraestructure.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nuwe.hackatonMWC.application.apis.ApiRESTCountries;
import io.nuwe.hackatonMWC.application.dto.CountryDTO;

@Service
public class CountriesService {

	@Autowired
	ApiRESTCountries restCountries;
	
	public List<CountryDTO> getCountriesList() {
		List<CountryDTO> countriesList = restCountries.getCountryList();
		if(countriesList==null) throw new NoSuchElementException();
		return countriesList;
	}
}
