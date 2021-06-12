package io.nuwe.hackatonMWC.controller;

import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import io.nuwe.hackatonMWC.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping
	public ResponseEntity<Object> newUser(@Valid @RequestBody User user) {
		try {
			UserDTO userDTO = userService.newUser(user);
			return new ResponseEntity<>(userDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("The user cannot be created./n" + e.getMessage(),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable("id") String id, @Valid @RequestBody User user) {
		try {
			UserDTO userDTO = userService.updateUser(user, id);
			return new ResponseEntity<>(userDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("The user cannot be updated./n" + e.getMessage(),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getUser(@PathVariable("id") String id) {
		try {
			UserDTO userDTO = userService.getUserById(id);
			return new ResponseEntity<>(userDTO, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("No user found with id: " + id, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
		try {
			userService.deleteUserById(id);
			return new ResponseEntity<>("User deleted correctly.", HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("No user found with id: " + id, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}
