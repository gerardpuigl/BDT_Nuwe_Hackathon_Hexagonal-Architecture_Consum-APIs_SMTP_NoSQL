package io.nuwe.hackatonMWC.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.nuwe.hackatonMWC.domain.Country;

@Repository
public interface CountryRepository extends MongoRepository<Country, String> {
	

}
