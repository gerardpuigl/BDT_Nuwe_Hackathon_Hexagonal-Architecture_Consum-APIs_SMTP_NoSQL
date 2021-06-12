package io.nuwe.hackatonMWC.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nuwe.hackatonMWC.domain.GitProfile;
import io.nuwe.hackatonMWC.domain.GitProfileType;
import io.nuwe.hackatonMWC.repository.GitProfileRepository;


@Service
public class GitProfileService {
		
	@Autowired
	private GitProfileRepository gitProfileRepository;

	//To map entity to DTO.
	@Autowired
	ModelMapper modelMapper;

	public GitProfile getGitLabProfile(String id) {
		GitProfile gitProfile = gitProfileRepository.getByUserId(id);
		if (gitProfile.getType().equals(GitProfileType.GITLABUSER)) return gitProfile;
		return null;
	}
	
	public GitProfile getGitHubProfile(String id) {
		GitProfile gitProfile = gitProfileRepository.getByUserId(id);
		if (gitProfile.getType().equals(GitProfileType.GITHUBUSER)) return gitProfile;
		return null;
	}
	
	

	
}
