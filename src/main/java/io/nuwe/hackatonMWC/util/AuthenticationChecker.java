package io.nuwe.hackatonMWC.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.nuwe.hackatonMWC.dto.UserDTO;
import io.nuwe.hackatonMWC.service.UserService;

@Component
public class AuthenticationChecker {
	
	@Autowired
	UserService userService;
	
	public void checkAuthUserAndId(Authentication auth, String id) {
		UserDTO userDTO = userService.findUserByUsername(auth.getName());
		if(!userDTO.getId().equals(id)) throw new SecurityException("The Jwt User and id sent don't match");
	}
	
}
