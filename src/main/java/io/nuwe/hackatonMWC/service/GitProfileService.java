package io.nuwe.hackatonMWC.service;

import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import io.nuwe.hackatonMWC.domain.User;
import io.nuwe.hackatonMWC.dto.GithubDTO;
import io.nuwe.hackatonMWC.dto.GitlabDTO;
import io.nuwe.hackatonMWC.repository.UserRepository;
import io.nuwe.hackatonMWC.util.ApiGithub;
import io.nuwe.hackatonMWC.util.ApiGitlab;


@Service
public class GitProfileService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ApiGithub apiGithub;
	
	@Autowired
	private ApiGitlab apiGitlab;

	//To map entity to DTO.
	@Autowired
	ModelMapper modelMapper;

	public GitlabDTO getGitLabProfile(String id) throws NotFoundException {
		User user = getUserById(id);
		GitlabDTO gitlabDTO = apiGitlab.getGitlabCredentials(user.getGitlabUserId());
		return gitlabDTO;
	}
	
	public GithubDTO getGitHubProfile(String id) throws NotFoundException {
		User user = getUserById(id);
		GithubDTO githubDTO = apiGithub.getGithubCredentials(user.getGithubUserId());
		return githubDTO;
	}

	public GitlabDTO postGitLabProfile(String username, String id) throws NotFoundException {
		User user = getUserById(id);
		user.setGithubUserId(username);
		userRepository.save(user);
		GitlabDTO gitlabDTO = apiGitlab.getGitlabCredentials(user.getGithubUserId());
		return gitlabDTO;
	}
	
	public GithubDTO postGitHubProfile(String username, String id) throws NotFoundException {
		User user = getUserById(id);
		user.setGithubUserId(username);
		userRepository.save(user);
		GithubDTO githubDTO = apiGithub.getGithubCredentials(user.getGithubUserId());
		return githubDTO;
	}

	private User getUserById(String id) {
		if(!userRepository.existsById(id)) throw new NoSuchElementException();
		User user = userRepository.findById(id).
				orElseThrow(() -> new NoSuchElementException("No user with this id: " + id));
		return user;
	}

	
}
