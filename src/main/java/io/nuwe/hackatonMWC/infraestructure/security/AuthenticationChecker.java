package io.nuwe.hackatonMWC.infraestructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.nuwe.hackatonMWC.application.services.UserService;
import io.nuwe.hackatonMWC.infraestructure.dto.UserDTO;

@Component
public class AuthenticationChecker {
	
	@Autowired
	UserService userService;
	
	public void checkAuthUserAndId(Authentication auth, String id) {
		UserDTO userDTO = userService.findUserByUsername(auth.getName());
		if(!userDTO.getId().equals(id)) throw new SecurityException("The Jwt User and id sent don't match");
	}

	public void checkAuthUserAndUsername(Authentication auth, String username) {
		if(!auth.getName().equals(username)) throw new SecurityException("The Jwt User and username sent don't match");
	}
	
}
