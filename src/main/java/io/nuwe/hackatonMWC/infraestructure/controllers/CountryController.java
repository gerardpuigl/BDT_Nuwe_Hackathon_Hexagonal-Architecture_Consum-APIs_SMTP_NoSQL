package io.nuwe.hackatonMWC.infraestructure.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nuwe.hackatonMWC.application.services.CountriesService;
import io.nuwe.hackatonMWC.infraestructure.dto.CountryDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "04 - Countries Api")
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
