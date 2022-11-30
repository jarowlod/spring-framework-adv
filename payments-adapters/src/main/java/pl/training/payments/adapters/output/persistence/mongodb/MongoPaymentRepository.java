package pl.training.payments.adapters.output.persistence.mongodb;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoPaymentRepository extends MongoRepository<PaymentDocument, String> {

    Page<PaymentDocument> getByStatus(String status, Pageable pageable);

}
