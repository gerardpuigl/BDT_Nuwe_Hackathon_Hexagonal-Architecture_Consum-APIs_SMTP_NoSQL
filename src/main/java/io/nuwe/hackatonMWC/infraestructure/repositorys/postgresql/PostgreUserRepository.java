package io.nuwe.hackatonMWC.infraestructure.repositorys.postgresql;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.nuwe.hackatonMWC.domain.entities.User;
import io.nuwe.hackatonMWC.domain.repository.IUserRepository;
import io.nuwe.hackatonMWC.infraestructure.repositorys.postgresql.entities.PostgreUserEntity;

//@Component
public class PostgreUserRepository implements IUserRepository {

	@Autowired
    private final IPostgreUserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

    @Autowired
    public PostgreUserRepository(final IPostgreUserRepository userRepository) {
        this.userRepository = userRepository;
    }

	@Override
	public User findByEmail(String email) {
		PostgreUserEntity user = userRepository.findByEmail(email);
		return modelMapper.map(user, User.class);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		PostgreUserEntity userPostgre = userRepository.findByUsername(username).get();
		User user = null;
		if (userPostgre != null) user = modelMapper.map(userPostgre, User.class);
		return Optional.ofNullable(user);
	}

	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public Optional<User> findById(String id) {
		PostgreUserEntity userPostgre = userRepository.findById(id).get();
		User user = null;
		if (userPostgre != null) user = modelMapper.map(userPostgre, User.class);
		return Optional.ofNullable(user);
	}

	@Override
	public void delete(User user) {
		PostgreUserEntity userPostgre = modelMapper.map(user, PostgreUserEntity.class);
		userRepository.delete(userPostgre);
	}

	@Override
	public User save(User user) {
		PostgreUserEntity userPostgre = modelMapper.map(user, PostgreUserEntity.class);
		userPostgre = userRepository.save(userPostgre);
		return modelMapper.map(userPostgre,User.class);
	}

	@Override
	public boolean existsById(String id) {
		return userRepository.existsById(id);
	}


}
