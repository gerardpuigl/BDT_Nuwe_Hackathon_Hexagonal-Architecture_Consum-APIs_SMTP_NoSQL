package io.nuwe.hackatonMWC.application.repositorys;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import io.nuwe.hackatonMWC.domain.entities.User;
import io.nuwe.hackatonMWC.domain.repository.IUserRepository;


@Component
@Primary
public class MongoUserRepository implements IUserRepository {

    private final IMongoUserRepository userRepository;

    @Autowired
    public MongoUserRepository(final IMongoUserRepository userRepository) {
        this.userRepository = userRepository;
    }

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public Optional<User> findById(String id) {
		return userRepository.findById(id);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public boolean existsById(String id) {
		return userRepository.existsById(id);
	}


}
