package io.nuwe.hackatonMWC.service;

import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nuwe.hackatonMWC.domain.GitProfile;
import io.nuwe.hackatonMWC.domain.GitProfileType;
import io.nuwe.hackatonMWC.repository.GitProfileRepository;
import io.nuwe.hackatonMWC.repository.UserRepository;


@Service
public class GitProfileService {
		
	@Autowired
	private GitProfileRepository gitProfileRepository;
	
	@Autowired
	private UserRepository userRepository;

	//To map entity to DTO.
	@Autowired
	ModelMapper modelMapper;

	public GitProfile getGitLabProfile(String id) {
		GitProfile gitProfile = gitProfileRepository.getByUserId(id);
		if (gitProfile.getType().equals(GitProfileType.GITLAB)) return gitProfile;
		return null;
	}
	
	public GitProfile getGitHubProfile(String id) {
		GitProfile gitProfile = gitProfileRepository.getByUserId(id);
		if (gitProfile.getType().equals(GitProfileType.GITHUB)) return gitProfile;
		return null;
	}

	public GitProfile postGitLabProfile(GitProfile gitProfile, String id) {
		if(!userRepository.existsById(id)) throw new NoSuchElementException();
		gitProfile.setType(GitProfileType.GITLAB);
		gitProfile.setUserId(id);
		GitProfile gitProfileDB = gitProfileRepository.save(gitProfile);
		return gitProfileDB;
	}
	
	public GitProfile postGitHubProfile(GitProfile gitProfile, String id) {
		if(!userRepository.existsById(id)) throw new NoSuchElementException();
		gitProfile.setType(GitProfileType.GITHUB);
		gitProfile.setUserId(id);
		GitProfile gitProfileDB = gitProfileRepository.save(gitProfile);
		return gitProfileDB;
	}

	
}
