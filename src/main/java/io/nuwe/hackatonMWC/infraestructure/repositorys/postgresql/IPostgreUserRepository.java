package io.nuwe.hackatonMWC.infraestructure.repositorys.postgresql;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.nuwe.hackatonMWC.infraestructure.repositorys.postgresql.entities.PostgreUserEntity;

@Repository
public interface IPostgreUserRepository extends JpaRepository<PostgreUserEntity, String> {
	
	public PostgreUserEntity findByEmail(String email);

	public Optional<PostgreUserEntity> findByUsername(String username);

	public boolean existsByUsername(String username);
}
