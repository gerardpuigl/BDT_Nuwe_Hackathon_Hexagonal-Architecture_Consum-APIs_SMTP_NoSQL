package io.nuwe.hackatonMWC.infraestructure.controllers;

import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nuwe.hackatonMWC.application.services.GitProfileService;
import io.nuwe.hackatonMWC.application.services.UserService;
import io.nuwe.hackatonMWC.domain.entities.User;
import io.nuwe.hackatonMWC.infraestructure.dto.UserDTO;
import io.nuwe.hackatonMWC.infraestructure.security.AuthenticationChecker;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	GitProfileService gitProfileService;
	
	@Autowired
	AuthenticationChecker authenticationChecker;
	
	@PostMapping
	public ResponseEntity<Object> newUser(@Valid @RequestBody User user) {
		try {
			UserDTO userDTO = userService.newUser(user);
			return new ResponseEntity<>(userDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("The user cannot be created.\n" + e.getMessage(),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUser(Authentication auth, @PathVariable("id") String id, @Valid @RequestBody User user) {
		try {
			authenticationChecker.checkAuthUserAndId(auth,id);
						
			UserDTO userDTO = userService.updateUser(user, id);
			return new ResponseEntity<>(userDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("The user cannot be updated.\n" + e.getMessage(),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getUser(Authentication auth, @PathVariable("id") String id) {
		try {
			authenticationChecker.checkAuthUserAndId(auth,id);
			
			UserDTO userDTO = userService.findUserById(id);
			return new ResponseEntity<>(userDTO, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("No user found with id: " + id , HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (Exception e) {
			return new ResponseEntity<>("The user cannot be found.\n" + e.getMessage(),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@GetMapping("/name/{username}")
	public ResponseEntity<Object> getUserByName(Authentication auth, @PathVariable("username") String username) {
		try {
			authenticationChecker.checkAuthUserAndUsername(auth,username);
			
			UserDTO userDTO = userService.findUserByUsername(username);
			return new ResponseEntity<>(userDTO, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("No user found with username: " + username , HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (Exception e) {
			return new ResponseEntity<>("The user cannot be found.\n" + e.getMessage(),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(Authentication auth, @PathVariable("id") String id) {
		try {
			authenticationChecker.checkAuthUserAndId(auth,id);
			
			userService.deleteUserById(id);
			return new ResponseEntity<>("User deleted correctly.", HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("No user found with id: " + id, HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (Exception e) {
			return new ResponseEntity<>("The user cannot be deleted.\n" + e.getMessage(),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

}
