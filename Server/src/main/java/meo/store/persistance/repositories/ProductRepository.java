package meo.store.persistance.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import meo.store.persistance.domain.Product;

public interface ProductRepository extends MongoRepository<Product, UUID> {
}
