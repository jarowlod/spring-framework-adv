package pl.training.broker.payments.persistence.sql;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface JpaPaymentRepository extends ReactiveCrudRepository<PaymentEntity, Long> {
}
