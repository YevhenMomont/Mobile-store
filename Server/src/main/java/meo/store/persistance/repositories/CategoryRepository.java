package meo.store.persistance.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import meo.store.persistance.domain.Category;

public interface CategoryRepository extends MongoRepository<Category, UUID> {
}
