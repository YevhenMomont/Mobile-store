package meo.store.persistance.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import meo.store.persistance.domain.Product;

public interface ProductRepository extends MongoRepository<Product, UUID> {

	List<Optional<Product>> findAllByCategoryId(UUID uuid);

	List<Product> findAllByUuidIn(List<UUID> uuids);

}
