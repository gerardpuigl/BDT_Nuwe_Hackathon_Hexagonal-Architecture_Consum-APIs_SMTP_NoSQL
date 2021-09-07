package io.nuwe.hackatonMWC.application.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nuwe.hackatonMWC.infraestructure.apis.ApiRESTCountries;
import io.nuwe.hackatonMWC.infraestructure.dto.CountryDTO;

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
