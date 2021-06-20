package io.nuwe.hackatonMWC.controller;

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

import io.nuwe.hackatonMWC.domain.User;
import io.nuwe.hackatonMWC.dto.UserDTO;
import io.nuwe.hackatonMWC.service.GitProfileService;
import io.nuwe.hackatonMWC.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	GitProfileService gitProfileService;
	
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
			checkAuthAndId(auth, id);
						
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
			checkAuthAndId(auth, id);
			UserDTO userDTO = userService.findUserById(id);
			return new ResponseEntity<>(userDTO, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("No user found with id: " + id , HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (Exception e) {
			return new ResponseEntity<>("The user cannot be found.\n" + e.getMessage(),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(Authentication auth, @PathVariable("id") String id) {
		try {
			checkAuthAndId(auth, id);
			userService.deleteUserById(id);
			return new ResponseEntity<>("User deleted correctly.", HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("No user found with id: " + id, HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (Exception e) {
			return new ResponseEntity<>("The user cannot be deleted.\n" + e.getMessage(),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	private void checkAuthAndId(Authentication auth, String id) {
		UserDTO userDTO = userService.findUserByUsername(auth.getName());

		// Check if the the requested ID matches the authorized user or is admin.
		if(!userDTO.getId().equals(id))throw new SecurityException("The Jwt and the id sent don't match");
	}

}
