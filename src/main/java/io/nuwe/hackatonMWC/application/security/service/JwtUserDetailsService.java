package io.nuwe.hackatonMWC.application.security.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.nuwe.hackatonMWC.domain.repository.IUserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	/**
	 * Here, since this is a basic application for the sole purpose of the
	 * demonstration of JWT authentication, we have resorted to a set of our user
	 * details, instead of using a database.
	 */

	@Autowired
	IUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		io.nuwe.hackatonMWC.domain.entities.User userDB = userRepository.findByUsername(username)
				.orElseThrow(()-> new UsernameNotFoundException("User not found with username: " + username));

		return new User(userDB.getUsername(), userDB.getPassword(), new ArrayList<>());
	}
}