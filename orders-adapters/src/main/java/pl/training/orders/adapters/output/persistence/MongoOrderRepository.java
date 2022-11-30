package pl.training.orders.adapters.output.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoOrderRepository extends MongoRepository<OrderDocument, String> {
}
