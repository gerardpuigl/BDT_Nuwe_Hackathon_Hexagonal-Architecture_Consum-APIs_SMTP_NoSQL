package io.nuwe.hackatonMWC.infraestructure.repositorys.mongodb;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import io.nuwe.hackatonMWC.domain.entities.User;
import io.nuwe.hackatonMWC.domain.repository.IUserRepository;
import io.nuwe.hackatonMWC.infraestructure.repositorys.mongodb.entities.MongoUserEntity;

@Component
@Primary
public class MongoUserRepository implements IUserRepository {

	@Autowired
	private final IMongoUserRepository userRepository;

	// To map entity to DTO.
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	public MongoUserRepository(final IMongoUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User findByEmail(String email) {
		MongoUserEntity user = userRepository.findByEmail(email);
		return modelMapper.map(user, User.class);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		MongoUserEntity userMongo = userRepository.findByUsername(username).get();
		User user = null;
		if (userMongo != null) user = modelMapper.map(userMongo, User.class);
		return Optional.ofNullable(user);
	}

	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public Optional<User> findById(String id) {
		MongoUserEntity userMongo = userRepository.findById(id).get();
		User user = null;
		if (userMongo != null) user = modelMapper.map(userMongo, User.class);
		return Optional.ofNullable(user);
	}

	@Override
	public void delete(User user) {
		MongoUserEntity userMongo = modelMapper.map(user, MongoUserEntity.class);
		userRepository.delete(userMongo);
	}

	@Override
	public User save(User user) {
		MongoUserEntity userMongo = modelMapper.map(user, MongoUserEntity.class);
		userMongo = userRepository.save(userMongo);
		return modelMapper.map(userMongo,User.class);
	}

	@Override
	public boolean existsById(String id) {
		return userRepository.existsById(id);
	}

}
