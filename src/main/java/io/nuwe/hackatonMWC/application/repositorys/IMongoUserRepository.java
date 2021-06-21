package io.nuwe.hackatonMWC.application.repositorys;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.nuwe.hackatonMWC.domain.entities.User;

@Repository
public interface IMongoUserRepository extends MongoRepository<User, String> {
	
	public User findByEmail(String email);

	public Optional<User> findByUsername(String username);

	public boolean existsByUsername(String username);
}
