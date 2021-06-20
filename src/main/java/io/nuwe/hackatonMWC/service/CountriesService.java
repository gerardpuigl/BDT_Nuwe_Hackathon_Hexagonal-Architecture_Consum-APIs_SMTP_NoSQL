package io.nuwe.hackatonMWC.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nuwe.hackatonMWC.dto.CountryDTO;
import io.nuwe.hackatonMWC.util.ApiRESTCountries;

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
