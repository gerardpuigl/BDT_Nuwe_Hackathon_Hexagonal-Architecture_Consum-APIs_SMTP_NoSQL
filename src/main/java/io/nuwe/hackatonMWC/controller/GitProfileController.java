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

import io.nuwe.hackatonMWC.domain.GitProfile;
import io.nuwe.hackatonMWC.domain.User;
import io.nuwe.hackatonMWC.dto.UserDTO;
import io.nuwe.hackatonMWC.service.GitProfileService;
import io.nuwe.hackatonMWC.service.UserService;

@RestController
@RequestMapping
public class GitProfileController {

	@Autowired
	UserService userService;

	@Autowired
	GitProfileService gitProfileService;

	@GetMapping("user/{id}/gitlab")
	public ResponseEntity<Object> getGitLabProfile(@PathVariable("id") String id) {
		try {
			GitProfile gitProfile = gitProfileService.getGitLabProfile(id);
			if (gitProfile != null) {
				return new ResponseEntity<>(gitProfile, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("The user don't have GitLabProfile.", HttpStatus.NOT_FOUND);
			}
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("No user found with id: \n" + id, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@GetMapping("user/{id}/github")
	public ResponseEntity<Object> getGitHubProfile(@PathVariable("id") String id) {
		try {
			GitProfile gitProfile = gitProfileService.getGitHubProfile(id);
			if (gitProfile != null) {
				return new ResponseEntity<>(gitProfile, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("The user don't have GitLabProfile.", HttpStatus.NOT_FOUND);
			}
		} catch (NoSuchElementException | NullPointerException e) {
			return new ResponseEntity<>("No user found with id: \n" + id, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@PostMapping("/gitlab/{id}")
	public ResponseEntity<Object> postGitLabProfile(@RequestBody GitProfile gitProfile, @PathVariable("id") String id) {
		try {
			GitProfile gitProfileDB = gitProfileService.postGitLabProfile(gitProfile, id);
			return new ResponseEntity<>(gitProfileDB, HttpStatus.OK);
		} catch (NoSuchElementException | NullPointerException e) {
			return new ResponseEntity<>("No user found with id: \n" + id, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PostMapping("/github/{id}")
	public ResponseEntity<Object> postGitHubProfile(@RequestBody GitProfile gitProfile, @PathVariable("id") String id) {
		try {
			GitProfile gitProfileDB = gitProfileService.postGitHubProfile(gitProfile, id);
			return new ResponseEntity<>(gitProfileDB, HttpStatus.OK);
		} catch (NoSuchElementException | NullPointerException e) {
			return new ResponseEntity<>("No user found with id: \n" + id, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}
