package io.nuwe.hackatonMWC.application.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nuwe.hackatonMWC.application.dto.CountryDTO;
import io.nuwe.hackatonMWC.infraestructure.services.CountriesService;


@RestController
public class CountryController {

	@Autowired
	CountriesService countriesService;

	@GetMapping("/countries")
	public ResponseEntity<Object> getCountriesList() {
		try {
			List<CountryDTO> countriesList = countriesService.getCountriesList();
			return new ResponseEntity<>(countriesList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Cannot get country list.\n" + e.getMessage(),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

}
