package io.nuwe.hackatonMWC.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.nuwe.hackatonMWC.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	public User findByEmail(String email);

}
