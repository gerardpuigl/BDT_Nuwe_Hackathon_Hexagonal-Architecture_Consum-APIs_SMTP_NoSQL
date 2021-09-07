package io.nuwe.hackatonMWC.infraestructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nuwe.hackatonMWC.application.services.UserService;
import io.nuwe.hackatonMWC.infraestructure.dto.UserDTO;
import io.nuwe.hackatonMWC.infraestructure.email.EmailService;


@RestController
@RequestMapping("/user/{id}")
public class EmailController {

	@Autowired
	UserService userService;
	
	@Autowired
	EmailService emailService;

	@GetMapping("/notification")
	public ResponseEntity<String> getCountriesList(@PathVariable("id") String id) {
		try {
			UserDTO user = userService.findUserById(id);
			emailService.sendNotificationEmail(user);
			return new ResponseEntity<String>("Email sent to user id: " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Email can't be sent.\n" + e.getMessage(),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

}
