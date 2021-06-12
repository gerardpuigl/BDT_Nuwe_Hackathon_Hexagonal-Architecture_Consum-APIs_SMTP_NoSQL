package io.nuwe.hackatonMWC.controller;

import java.util.NoSuchElementException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nuwe.hackatonMWC.dto.UserDTO;
import io.nuwe.hackatonMWC.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;

	@GetMapping("/{id}")
	public ResponseEntity<Object> getUser(@PathVariable("id") int id) {
		try {
			UserDTO userDTO = userService.getUserById(id);
			return new ResponseEntity<>(userDTO, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("No user found with id: " + id, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
		try {
			userService.deleteUserById(id);
			return new ResponseEntity<>("User deleted correctly.", HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("No user found with id: " + id, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	

	//TODO  DELETE /user/:id
	//Descripción: Se puede borrar objeto user a través de su ID

	//TODO  PUT /user/:id
    //Descripción: Se puede actualizar un usuario a través de su ID

	//TODO  POST /user
    //Descripción: Crea un usuario

	//TODO  GET /user/:id/gitlab
    //Description: Devuele los datos de usuario del modelo de githubUser entrando dándo el nombre de usuario gitlab

	//TODO  GET /user/:id/github 
	//Description: Devuele los datos de usuario del modelo de githubUser entrando dándo el nombre de usuario github
	
	
	
}
