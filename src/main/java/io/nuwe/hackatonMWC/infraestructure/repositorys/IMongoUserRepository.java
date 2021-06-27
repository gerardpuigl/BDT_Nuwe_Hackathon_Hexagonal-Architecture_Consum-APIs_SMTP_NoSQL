package io.nuwe.hackatonMWC.infraestructure.repositorys;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMongoUserRepository extends MongoRepository<MongoUserEntity, String> {
	
	public MongoUserEntity findByEmail(String email);

	public Optional<MongoUserEntity> findByUsername(String username);

	public boolean existsByUsername(String username);
}
