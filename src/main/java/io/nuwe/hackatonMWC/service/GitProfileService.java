package io.nuwe.hackatonMWC.service;

import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import io.nuwe.hackatonMWC.domain.User;
import io.nuwe.hackatonMWC.dto.GithubDTO;
import io.nuwe.hackatonMWC.repository.UserRepository;
import io.nuwe.hackatonMWC.util.ApiGithub;


@Service
public class GitProfileService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ApiGithub apiGithub;

	//To map entity to DTO.
	@Autowired
	ModelMapper modelMapper;

	public GithubDTO getGitLabProfile(String id) {
		return null;
	}
	
	public GithubDTO getGitHubProfile(String id) throws NotFoundException {
		User user = getUserById(id);
		GithubDTO githubDTO = apiGithub.getGithubCredentials(user.getGithubUserId());
		return githubDTO;
	}

	public GithubDTO postGitLabProfile(String username, String id) {
		return null;
	}
	
	public GithubDTO postGitHubProfile(String username, String id) throws NotFoundException {
		User user = getUserById(id);
		user.setGithubUserId(username);
		GithubDTO githubDTO = apiGithub.getGithubCredentials(user.getGithubUserId());
		return githubDTO;
	}

	public User getUserById(String id) {
		if(!userRepository.existsById(id)) throw new NoSuchElementException();
		User user = userRepository.findById(id).get();
		return user;
	}

	
}
