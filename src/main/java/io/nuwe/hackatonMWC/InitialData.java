package io.nuwe.hackatonMWC;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import io.nuwe.hackatonMWC.domain.GitProfile;
import io.nuwe.hackatonMWC.domain.GitProfileType;
import io.nuwe.hackatonMWC.domain.User;
import io.nuwe.hackatonMWC.repository.GitProfileRepository;
import io.nuwe.hackatonMWC.repository.UserRepository;

@Component
public class InitialData {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GitProfileRepository gitProfileRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostConstruct
	public void populate() {
		
		User user1 = new User("60c4b7968c71c14b521ed76a", "TestUser01", "User011", "TestUser01@nuwe.io", true, passwordEncoder.encode("hackathonMWC"), "git1", "ES");
		User user2 = new User("60c4b7968c71c14b521ed76b", "TestUser02", "User02", "TestUser02@nuwe.io", true, passwordEncoder.encode("hackathonMWC"), "git2", "ES");
		userRepository.save(user2);
		userRepository.save(user1);
		
		GitProfile gitProfile1 = new GitProfile("60c4dfffb8a5994017ddd300", "60c4b7968c71c14b521ed76a", "User01Git", "https://github.com/user01Git", GitProfileType.GITHUB, null, null);
		GitProfile gitProfile2 = new GitProfile("60c4dfffb8a5994017ddd301", "60c4b7968c71c14b521ed76b", "User02Git", "https://github.com/user02Git", GitProfileType.GITHUB, null, null);
		gitProfileRepository.save(gitProfile1);
		gitProfileRepository.save(gitProfile2);
		
	};

}
