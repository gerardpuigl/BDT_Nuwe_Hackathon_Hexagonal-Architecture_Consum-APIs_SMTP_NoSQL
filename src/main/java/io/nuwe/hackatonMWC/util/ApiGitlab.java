package io.nuwe.hackatonMWC.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import io.nuwe.hackatonMWC.dto.GitlabDTO;
import io.nuwe.hackatonMWC.dto.GitlabRepoDTO;

@Component
public class ApiGitlab {
	
    @Autowired
    WebClient webClient;
    
	public GitlabDTO getGitlabCredentials(String username) throws NotFoundException {
		GitlabDTO gitlabDTO = webClient.get()
			.uri("https://gitlab.com/api/v4/users?username=" + username)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToFlux(GitlabDTO.class)
			.blockFirst();
		
		gitlabDTO.setRepositoriesURL("https://gitlab.com/users/"+username+"/projects");
		
		List<GitlabRepoDTO> githubReposUrl = webClient.get()
				.uri("https://gitlab.com/api/v4/users/"+gitlabDTO.getId()+"/projects")
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(GitlabRepoDTO.class)
				.buffer().blockLast();
		
		gitlabDTO.setRepositories(githubReposUrl);
		
		return gitlabDTO;
	}

}
