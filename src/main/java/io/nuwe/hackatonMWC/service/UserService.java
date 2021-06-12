package io.nuwe.hackatonMWC.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nuwe.hackatonMWC.dto.UserDTO;
import io.nuwe.hackatonMWC.domain.User;
import io.nuwe.hackatonMWC.repository.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	//To map entity to DTO.
	@Autowired
	ModelMapper modelMapper;
	
	public UserDTO getUserById(int id) {
		User user =userRepository.findById(id).get();
		return modelMapper.map(user, UserDTO.class);
	}

	public void deleteUserById(int id) {
		User user =userRepository.findById(id).get();
		userRepository.delete(user);
	}

}
