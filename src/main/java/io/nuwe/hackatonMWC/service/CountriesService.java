package io.nuwe.hackatonMWC.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nuwe.hackatonMWC.domain.Country;
import io.nuwe.hackatonMWC.util.RESTCountries;

@Service
public class CountriesService {

	@Autowired
	RESTCountries restCountries;
	
	public List<Country> getCountriesList() {
		List<Country> countriesList = restCountries.getCountryList();
		if(countriesList==null) throw new NoSuchElementException();
		return countriesList;
	}




}
