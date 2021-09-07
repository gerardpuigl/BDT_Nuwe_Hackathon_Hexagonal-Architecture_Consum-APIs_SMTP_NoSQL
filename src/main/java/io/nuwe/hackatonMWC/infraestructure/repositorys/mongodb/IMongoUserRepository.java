package io.nuwe.hackatonMWC.infraestructure.repositorys.mongodb;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.nuwe.hackatonMWC.infraestructure.repositorys.mongodb.entities.MongoUserEntity;

@Repository
public interface IMongoUserRepository extends MongoRepository<MongoUserEntity, String> {
	
	public MongoUserEntity findByEmail(String email);

	public Optional<MongoUserEntity> findByUsername(String username);

	public boolean existsByUsername(String username);
}
