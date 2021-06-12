package io.nuwe.hackatonMWC.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.nuwe.hackatonMWC.domain.GitProfile;

@Repository
public interface GitProfileRepository extends MongoRepository<GitProfile, String> {

	public GitProfile getByUserId(String id);
}
