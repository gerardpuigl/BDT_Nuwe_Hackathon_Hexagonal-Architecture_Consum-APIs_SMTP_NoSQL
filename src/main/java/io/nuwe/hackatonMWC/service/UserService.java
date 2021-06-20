package io.nuwe.hackatonMWC.service;

import java.util.InputMismatchException;
import java.util.InvalidPropertiesFormatException;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.nuwe.hackatonMWC.dto.UserDTO;
import io.nuwe.hackatonMWC.email.EmailServiceImpl;
import io.nuwe.hackatonMWC.exception.AlreadyExistsException;
import io.nuwe.hackatonMWC.domain.User;
import io.nuwe.hackatonMWC.repository.UserRepository;
import io.nuwe.hackatonMWC.util.ApiMailboxlayer;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	ApiMailboxlayer apiMailboxlayer;

	@Autowired
	EmailServiceImpl emailService;

	// To map entity to DTO.
	@Autowired
	ModelMapper modelMapper;

	public UserDTO findUserById(String id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("No user with this id: " + id));
		return modelMapper.map(user, UserDTO.class);
	}

	public UserDTO findUserByUsername(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new NoSuchElementException("No user with this username: " + username));
		return modelMapper.map(user, UserDTO.class);
	}

	public void deleteUserById(String id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("No user with this id: " + id));
		userRepository.delete(user);
	}

	public UserDTO newUser(User user) throws AlreadyExistsException, InvalidPropertiesFormatException {

		// Check if the username is used.
		checkUsername(user);

		// Check email using MailboxLayer
		apiMailboxlayer.checkEmail(user.getEmail());

		// encode Password
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		User userDB = userRepository.save(user);
		emailService.sendWelcomeEmail(userDB);
		return modelMapper.map(userDB, UserDTO.class);
	}

	public UserDTO updateUser(User user, String id) throws AlreadyExistsException, InvalidPropertiesFormatException {

		// Check if path id is equal than user.id
		if (!user.getId().equals(id)) throw new InputMismatchException("The path is diferent from id in Json");
		
		// Check if user exist & get it
		User userDB = userRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("No user with this id: " + id));

		// Check if the user name is diferent and check if the new one is used.
		if (!userDB.getName().equals(user.getName())) checkUsername(user);

		// Check email using MailboxLayer
		apiMailboxlayer.checkEmail(user.getEmail());
		
		// encode Password
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		user = userRepository.save(user);
		return modelMapper.map(user, UserDTO.class);
	}

	private void checkUsername(User user) throws AlreadyExistsException {
		if (userRepository.existsByUsername(null))
			throw new AlreadyExistsException("Username already in use.");
	}

}
