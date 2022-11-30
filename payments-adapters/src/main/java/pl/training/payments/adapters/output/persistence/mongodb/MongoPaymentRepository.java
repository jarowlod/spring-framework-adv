package pl.training.payments.adapters.output.persistence.mongodb;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface MongoPaymentRepository extends MongoRepository<PaymentDocument, String> {

    @Query("{'status':  {'$eq': ?0}}")
    Page<PaymentDocument> getByStatus(String status, Pageable pageable);

    @Query(value = "{'id':  {'$eq': ?0}}", fields = "{'status': 1, 'id': 0}")
    Optional<PaymentDocumentProjection> getById(String id);

}
