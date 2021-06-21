package io.nuwe.hackatonMWC.application.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import io.nuwe.hackatonMWC.application.dto.GithubDTO;
import io.nuwe.hackatonMWC.application.dto.GithubRepoDTO;

@Component
public class ApiGithub {
	
    @Autowired
    WebClient webClient;
    
	public GithubDTO getGithubCredentials(String username) throws NotFoundException {
		GithubDTO githubDTO = webClient.get()
			.uri("https://api.github.com/users/" + username)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(GithubDTO.class)
			.block();
		
		List<GithubRepoDTO> githubReposUrl = webClient.get()
				.uri(githubDTO.getRepos_url())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(GithubRepoDTO.class)
				.buffer().blockLast();
		
		githubDTO.setRepositories(githubReposUrl);
		
		return githubDTO;
	}

}
