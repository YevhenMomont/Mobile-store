package meo.store.persistance.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import meo.store.persistance.domain.Record;

public interface RecordRepository extends MongoRepository<Record, UUID> {

	List<Optional<Record>> findAllByUserId(UUID uuid);

}
