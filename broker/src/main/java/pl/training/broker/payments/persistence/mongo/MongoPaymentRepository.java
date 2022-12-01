package pl.training.broker.payments.persistence.mongo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoPaymentRepository extends ReactiveMongoRepository<PaymentDocument, String> {
}
