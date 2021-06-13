package io.nuwe.hackatonMWC.service;

import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.nuwe.hackatonMWC.dto.UserDTO;
import io.nuwe.hackatonMWC.exception.AlreadyExistsException;
import io.nuwe.hackatonMWC.domain.User;
import io.nuwe.hackatonMWC.repository.UserRepository;


@Service
public class UserService {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;

	//To map entity to DTO.
	@Autowired
	ModelMapper modelMapper;
	
	public UserDTO getUserById(String id) {
		User user =userRepository.findById(id).get();
		return modelMapper.map(user, UserDTO.class);
	}

	public void deleteUserById(String id) {
		User user =userRepository.findById(id).get();
		userRepository.delete(user);
	}

	public UserDTO newUser(User user) throws AlreadyExistsException {
		if(userRepository.findByUsername(user.getUsername()) != null ) throw new AlreadyExistsException("Username already in use.");
		//encode Password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User userDB = userRepository.save(user);
		return modelMapper.map(userDB, UserDTO.class);
	}

	public UserDTO updateUser(User user, String id) throws AlreadyExistsException {
		//Check if user exist
		if(!userRepository.existsById(id)) throw new NoSuchElementException("No user with this id: " + id);
		if(userRepository.findByUsername(user.getUsername()) != null ) throw new AlreadyExistsException("Username already in use.");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User userDB = userRepository.save(user);
		return modelMapper.map(userDB, UserDTO.class);
	}
	
	
	
}
