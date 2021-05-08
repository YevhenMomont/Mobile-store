package meo.store.persistance.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import meo.store.persistance.domain.Order;

public interface OrderRepository extends MongoRepository<Order, UUID> {
}
