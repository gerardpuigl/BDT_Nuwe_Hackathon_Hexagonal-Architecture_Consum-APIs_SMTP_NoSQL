package io.nuwe.hackatonMWC.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nuwe.hackatonMWC.application.dto.GithubDTO;
import io.nuwe.hackatonMWC.application.dto.GitlabDTO;
import io.nuwe.hackatonMWC.infraestructure.services.GitProfileService;
import io.nuwe.hackatonMWC.infraestructure.services.UserService;

@RestController
@RequestMapping("/user/{id}")
public class GitProfileController {

	@Autowired
	UserService userService;

	@Autowired
	GitProfileService gitProfileService;

	@GetMapping("/gitlab")
	public ResponseEntity<Object> getGitLabProfile(@PathVariable("id") String id) {
		try {
			GitlabDTO gitlabDto = gitProfileService.getGitLabProfile(id);
			if (gitlabDto != null) {
				return new ResponseEntity<>(gitlabDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("The user don't have GitLabProfile.", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("No user found with id: \n" + id + e.getMessage() , HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@GetMapping("/github")
	public ResponseEntity<Object> getGitHubProfile(@PathVariable("id") String id) {
		try {
			GithubDTO githubDto = gitProfileService.getGitHubProfile(id);
			if (githubDto != null) {
				return new ResponseEntity<>(githubDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("The user don't have GitLabProfile.", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(//"No user found with id: \n" + id
					e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@PostMapping("/gitlab/{username}")
	public ResponseEntity<Object> postGitLabProfile(@PathVariable("id") String id, @PathVariable("username") String username) {
		try {
			GitlabDTO gitProfileDB = gitProfileService.postGitLabProfile(username, id);
			return new ResponseEntity<>(gitProfileDB, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("No user found with id: \n" + id, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PostMapping("/github/{username}")
	public ResponseEntity<Object> postGitHubProfile(@PathVariable("id") String id, @PathVariable("username") String username) {
		try {
			GithubDTO gitProfileDB = gitProfileService.postGitHubProfile(username, id);
			return new ResponseEntity<>(gitProfileDB, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("No user found with id: \n" + id, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}
