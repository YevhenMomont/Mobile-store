package meo.store.persistance.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import meo.store.persistance.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, UUID> {

	Optional<User> findByEmail(String email);
}
